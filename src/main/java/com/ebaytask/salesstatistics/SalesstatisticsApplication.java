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
@ComponentScan({"API", "Lib","com.ebaytask.salesstatistics.config", "com.ebaytask.salesstatistics.lib"})
public class SalesstatisticsApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication app = new SpringApplication(SalesstatisticsApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8083"));
        app.run(args);
    }

}
