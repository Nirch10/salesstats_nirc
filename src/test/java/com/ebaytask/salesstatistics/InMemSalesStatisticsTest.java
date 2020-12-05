package com.ebaytask.salesstatistics;

import Lib.ISalesStatisticsDal;
import Lib.InMemSalesStatisticsDal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

@SpringBootTest
public class InMemSalesStatisticsTest {

    static ISalesStatisticsDal statisticsDal;
    @BeforeAll
    static void testSetUp(){
        statisticsDal = new InMemSalesStatisticsDal();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void addToGuava(){
        int i = 0;
        while (true) {
            statisticsDal.add(100 * i);
            statisticsDal.add(100 * i);
            final Collection<Integer> statistics = statisticsDal.getStatistics();
            statistics.stream().forEach(x -> System.out.println(x));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

}
