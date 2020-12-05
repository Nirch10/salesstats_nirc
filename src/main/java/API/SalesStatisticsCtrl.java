package API;

import Lib.ISalesStatisticsDal;
import Lib.InMemSalesStatisticsDal;
import Lib.TransactionsStatistics;
import com.ebaytask.salesstatistics.config.EbayTaskConfigWrapper;
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
    public SalesStatisticsCtrl(ISalesStatisticsDal salesStatisticsDal){
        this.salesStatisticsDal = salesStatisticsDal;
    }

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
//        long statisticsSum = sumStatistics(statistics);
//        double statisticsAvg = 0.0;
//        if(statistics.size() > 0)
//            statisticsAvg = statisticsSum / statistics.size();
//        TransactionsStatistics ts = new TransactionsStatistics(statisticsSum, statisticsAvg);
//        return ts;
        return calcStatistics(statistics);
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

    private TransactionsStatistics calcStatistics(Collection<Integer> statistics){
        int count = (int) statistics.stream().mapToLong(statistic -> statistic).count();
        long sum = statistics.stream().mapToLong(statistic -> statistic).sum();
        if(count == 0)
            return new TransactionsStatistics(sum, count);
        return new TransactionsStatistics(sum, sum/ count);
    }

    private List<JSONObject> parseStatistics(long statisticsSum, double statisticsMean){
        List<JSONObject> responseData = new ArrayList<>();
        JSONObject entity = new JSONObject();
        entity.put("total_sales_amount", String.valueOf(statisticsSum));
        entity.put("average_amount_per_order", String.valueOf(statisticsMean));
        responseData.add(entity);
        return responseData;
    }

    public static void main(String[] args){

    }

}
