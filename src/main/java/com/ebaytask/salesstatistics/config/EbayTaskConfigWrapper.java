package com.ebaytask.salesstatistics.config;

import com.google.gson.Gson;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Configuration
@ComponentScan("Lib")
@Getter
public class EbayTaskConfigWrapper {

    public static EbayTaskConfig ebayTaskConfig;
    private final String EBAY_TASK_CONFIG_PATH = "src/main/resources/EbayTaskConfig.json";

    public EbayTaskConfigWrapper(){
        if(ebayTaskConfig == null) {
            try {
                ebayTaskConfig = Deserialize(getConfigAbsolutePath());
            }
            catch (IOException e) {
                e.printStackTrace();
                throw new NullPointerException();
            }
            catch (SecurityException e){
                throw new SecurityException();
            }
        }
    }

    /**
     * SpringBoot init bean for Initialization of ISalesStatisticsDal
     * @return Integer representing time to save each transaction in seconds (From config file)
     */
    @Bean
    public Integer SecondsToSaveTransaction() throws IOException {
        if(ebayTaskConfig == null){
                ebayTaskConfig = Deserialize(getConfigAbsolutePath());
        }
        return ebayTaskConfig.getSecondsToSaveTransaction();
    }

    public static EbayTaskConfig Deserialize(String configPath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(configPath)));
        Gson jsonParser = new Gson();
        ebayTaskConfig = jsonParser.fromJson(content, EbayTaskConfig.class);
        return ebayTaskConfig;
    }


    private String getConfigAbsolutePath() throws SecurityException {
        String path = new File(EBAY_TASK_CONFIG_PATH).getAbsolutePath();
        return path;
    }
}
