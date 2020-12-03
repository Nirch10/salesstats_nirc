package API;

import Lib.ISalesStatisticsDal;
import Lib.TransactionsStatistics;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class SalesStatisticsCtrl {

    private ISalesStatisticsDal salesStatisticsDal;


    public SalesStatisticsCtrl(ISalesStatisticsDal salesStatisticsDal){
        this.salesStatisticsDal = salesStatisticsDal;
    }

    @PostMapping("/sales")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addTransaction(@RequestBody String salesAmount) throws Exception {
        //TODO: Push To Redis + Handle Expections
        boolean addedSuccessfully = true;
        if(addedSuccessfully == false)
            throw new Exception();
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

    @GetMapping("/statistics")
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
