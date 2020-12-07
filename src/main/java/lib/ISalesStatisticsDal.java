package lib;

import java.util.Collection;

public interface ISalesStatisticsDal {

    /**
     * @param saleAmount - New Payment to be added.
     */
    void add(int saleAmount);

    /**
     * @return - Collection of current payments available in memory
     */
    Collection<Integer> getStatistics();
}
