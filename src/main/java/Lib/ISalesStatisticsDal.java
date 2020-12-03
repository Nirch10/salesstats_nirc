package Lib;

import java.util.Collection;

public interface ISalesStatisticsDal {
    public void add(int saleAmount);
    public Collection<Integer> getStatistics();
}
