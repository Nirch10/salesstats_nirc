package Lib;

import java.util.Collection;

public interface ISalesStatisticsDal {
    void add(int saleAmount);
    Collection<Integer> getStatistics();
}
