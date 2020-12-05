package Lib;

import com.ebaytask.salesstatistics.config.EbayTaskConfig;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class InMemSalesStatisticsDal implements ISalesStatisticsDal {

    private Cache<String , Integer> cache;
//    private Random uuidRandom;



    @Autowired
    public InMemSalesStatisticsDal(EbayTaskConfig config){
        cache = CacheBuilder.newBuilder().expireAfterWrite(config.getSecondsToSaveTransaction(), TimeUnit.SECONDS).build();
//        uuidRandom = new Random();
    }

    @Override
    public void add(int saleAmount) {
        cache.put(String.valueOf(UUID.randomUUID()), saleAmount);
//        cache.put(String.valueOf(new UUID(uuidRandom.nextLong(), uuidRandom.nextLong())), saleAmount);
    }

    @Override
    public Collection<Integer> getStatistics() {
        return cache.asMap().values();
    }
}
