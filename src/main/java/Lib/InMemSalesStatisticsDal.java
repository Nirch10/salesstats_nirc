package Lib;

import com.ebaytask.salesstatistics.config.EbayTaskConfig;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class InMemSalesStatisticsDal implements ISalesStatisticsDal {

    private LoadingCache<String, Integer> timeLimitedList;
//    private Cache<String , Integer> cache;

    @Autowired
    public InMemSalesStatisticsDal(EbayTaskConfig config){
        timeLimitedList = CacheBuilder.newBuilder()
                .expireAfterWrite(config.getSecondsToSaveTransaction(), TimeUnit.SECONDS)
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
