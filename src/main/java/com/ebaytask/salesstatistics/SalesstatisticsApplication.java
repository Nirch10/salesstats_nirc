package com.ebaytask.salesstatistics;

import com.ebaytask.salesstatistics.config.EbayTaskConfig;
import com.ebaytask.salesstatistics.config.EbayTaskConfigWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.Collections;

@SpringBootApplication
@ComponentScan({"API", "Lib","com.ebaytask.salesstatistics.config"})
public class SalesstatisticsApplication {

    public static void main(String[] args){
        final EbayTaskConfigWrapper ebayTaskConfigWrapper = new EbayTaskConfigWrapper();
        SpringApplication app = new SpringApplication(SalesstatisticsApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", ebayTaskConfigWrapper.ebayTaskConfig.getAppHttpPort()));
        app.run(args);
    }

}
