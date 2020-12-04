package Lib;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class InMemSalesStatisticsDal implements ISalesStatisticsDal {

    private LoadingCache<String, Integer> timeLimitedList;
//    private Cache<String , Integer> cache;


    public InMemSalesStatisticsDal(long timeLimitInSeconds){
        timeLimitedList = CacheBuilder.newBuilder()
                .expireAfterWrite(timeLimitInSeconds, TimeUnit.SECONDS)
                .build(
                        new CacheLoader<String, Integer>() {
                            @Override
                            public Integer load(String s) throws Exception {
                                return null;
                            }
                        }
                );
//        cache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).build();
    }

    @Override
    public void add(int saleAmount) {
        timeLimitedList.put(String.valueOf(UUID.randomUUID()), saleAmount);
    }

    @Override
    public Collection<Integer> getStatistics() {
        return timeLimitedList.asMap().values();
    }
}
