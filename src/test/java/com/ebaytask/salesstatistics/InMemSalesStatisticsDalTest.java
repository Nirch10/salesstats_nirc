package com.ebaytask.salesstatistics;

import lib.ISalesStatisticsDal;
import lib.InMemSalesStatisticsDal;
import com.ebaytask.salesstatistics.config.EbayTaskConfigWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SalesStatisticsApplication.class)

public class InMemSalesStatisticsDalTest {

    static ISalesStatisticsDal statisticsDal;
    static EbayTaskConfigWrapper configWrapper;
    @BeforeAll
    static void testSetUp(){
        configWrapper = new EbayTaskConfigWrapper();
        statisticsDal = new InMemSalesStatisticsDal(EbayTaskConfigWrapper.ebayTaskConfig.getSecondsToSaveTransaction());
    }

    @Test
    void testAddManyToCache(){
        int i = 0;
        while (i < 2000) {
            statisticsDal.add(100 * i);
            statisticsDal.add(100 * i);
            testGetAllInCache();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }

    }

    @Test
    void testGetAllInCache(){
            final Collection<Integer> statistics = statisticsDal.getStatistics();
            statistics.stream().forEach(x -> System.out.println(x));
            System.out.println("---------------");
    }

}
