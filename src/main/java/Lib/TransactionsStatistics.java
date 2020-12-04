package Lib;

import lombok.Getter;

@Getter
public class TransactionsStatistics {
    private Long total_sales_amount;
    private Double average_amount_per_order;

    public TransactionsStatistics(long total_sales_amount, double average_amount_per_order){
        this.total_sales_amount = total_sales_amount;
        this.average_amount_per_order = average_amount_per_order;
    }
}
