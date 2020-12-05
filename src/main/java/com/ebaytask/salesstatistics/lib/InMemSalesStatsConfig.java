package com.ebaytask.salesstatistics.lib;

import Lib.ISalesStatisticsDal;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Configuration
public class InMemSalesStatsConfig {
    class InMemSalesStatsDal implements ISalesStatisticsDal {

        private LoadingCache<String, Integer> timeLimitedList;
//    private Cache<String , Integer> cache;


        public InMemSalesStatsDal(long timeLimitInSeconds){
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

    @Bean
    public InMemSalesStatsDal inMemSalesStatsDal(){
        return new InMemSalesStatsDal(60);
    }
}
