package API;

import Lib.ISalesStatisticsDal;
import Lib.InMemSalesStatisticsDal;
import Lib.TransactionsStatistics;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class SalesStatisticsCtrl {

    private ISalesStatisticsDal salesStatisticsDal;


    @Autowired
    public SalesStatisticsCtrl(){
        this.salesStatisticsDal = new InMemSalesStatisticsDal(60);
    }
//    public SalesStatisticsCtrl(ISalesStatisticsDal salesStatisticsDal){
//        this.salesStatisticsDal = salesStatisticsDal;
//    }

    @PostMapping(value = "/sales", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addTransaction(@RequestBody MultiValueMap<String, String> salesAmount) {
        //TODO: Push To Redis + Handle Expections
        salesStatisticsDal.add(Integer.valueOf(salesAmount.getFirst("sales_amount")));
    }

    @GetMapping("/statistics")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TransactionsStatistics getStatistics(){
        Collection<Integer> statistics = salesStatisticsDal.getStatistics();
        long statisticsSum = sumStatistics(statistics);
        TransactionsStatistics ts = new TransactionsStatistics(statisticsSum, statisticsSum / statistics.size());
        return ts;
    }

    @GetMapping("/statistics2")
    @ResponseBody
    public ResponseEntity<Object> getStatistics2(){
        Collection<Integer> statistics = salesStatisticsDal.getStatistics();
        long statisticsSum = sumStatistics(statistics);
        return new ResponseEntity<Object>(parseStatistics(statisticsSum, statisticsSum / statistics.size()), HttpStatus.OK);
    }

    private long sumStatistics(Collection<Integer> statistics) {
        long sum = statistics.stream().mapToLong(statistic -> statistic).sum();
        return sum;
    }

    private List<JSONObject> parseStatistics(long statisticsSum, double statisticsMean){
        List<JSONObject> responseData = new ArrayList<>();
        JSONObject entity = new JSONObject();
        entity.put("total_sales_amount", String.valueOf(statisticsSum));
        entity.put("average_amount_per_order", String.valueOf(statisticsMean));
        responseData.add(entity);
        return responseData;
    }

}
