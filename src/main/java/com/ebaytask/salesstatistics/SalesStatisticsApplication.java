package com.ebaytask.salesstatistics;

import com.ebaytask.salesstatistics.config.EbayTaskConfigWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;

@SpringBootApplication
@ComponentScan({"api", "lib","com.ebaytask.salesstatistics.config"})
public class SalesStatisticsApplication {

    public static void main(String[] args){
        final EbayTaskConfigWrapper ebayTaskConfigWrapper = new EbayTaskConfigWrapper();
        SpringApplication app = new SpringApplication(SalesStatisticsApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", ebayTaskConfigWrapper.ebayTaskConfig.getAppHttpPort()));
        app.run(args);
    }

}
