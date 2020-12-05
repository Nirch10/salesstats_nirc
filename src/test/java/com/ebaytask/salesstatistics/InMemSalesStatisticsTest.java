package com.ebaytask.salesstatistics;

import Lib.ISalesStatisticsDal;
import Lib.InMemSalesStatisticsDal;
import com.ebaytask.salesstatistics.config.EbayTaskConfigWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SalesstatisticsApplication.class)

public class InMemSalesStatisticsTest {

    static ISalesStatisticsDal statisticsDal;
    static EbayTaskConfigWrapper configWrapper;
    @BeforeAll
    static void testSetUp() throws IOException {
        configWrapper = new EbayTaskConfigWrapper();
        statisticsDal = new InMemSalesStatisticsDal(EbayTaskConfigWrapper.ebayTaskConfig);
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
            statisticsDal.add(100 * i);
            statisticsDal.add(100 * i);
            statisticsDal.add(100 * i);
            statisticsDal.add(100 * i);
            statisticsDal.add(100 * i);
            statisticsDal.add(100 * i);
            statisticsDal.add(100 * i);
            showStats();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    @Test
    void showStats(){
        while(true) {
            final Collection<Integer> statistics = statisticsDal.getStatistics();
            statistics.stream().forEach(x -> System.out.println(x));
            System.out.println("---------------");

        }
    }

}
