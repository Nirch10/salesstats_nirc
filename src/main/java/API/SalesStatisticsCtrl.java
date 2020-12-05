package API;

import Lib.ISalesStatisticsDal;
import Lib.TransactionsStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

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
        salesStatisticsDal.add(Integer.valueOf(salesAmount.getFirst("sales_amount")));
    }

    @GetMapping(value = "/statistics", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TransactionsStatistics getStatistics(){
        Collection<Integer> statistics = salesStatisticsDal.getStatistics();
        return calcStatistics(statistics);
    }

    private TransactionsStatistics calcStatistics(Collection<Integer> statistics){
        int count = (int) statistics.stream().mapToLong(statistic -> statistic).count();
        if(count == 0)
            return new TransactionsStatistics(0, 0);
        long sum = statistics.stream().mapToLong(statistic -> statistic).sum();
        return new TransactionsStatistics(sum, sum / count);
    }

}
