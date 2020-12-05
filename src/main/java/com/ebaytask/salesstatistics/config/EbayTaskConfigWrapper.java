package com.ebaytask.salesstatistics.config;

import com.google.gson.Gson;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Configuration
@ComponentScan("Lib")
@Getter
public class EbayTaskConfigWrapper {

    public static EbayTaskConfig ebayTaskConfig;

    public EbayTaskConfigWrapper(){
        if(ebayTaskConfig == null) {
            try {
                ebayTaskConfig = Deserialize(getConfigPath());
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

    @Bean
    public EbayTaskConfig ebayTaskConfig(){
       if(ebayTaskConfig == null)
           return new EbayTaskConfig();
       return ebayTaskConfig;
    }

    public static EbayTaskConfig Deserialize(String configPath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(configPath)));
        Gson jsonParser = new Gson();
        ebayTaskConfig = jsonParser.fromJson(content, EbayTaskConfig.class);
        return ebayTaskConfig;
    }

    private String getConfigPath() throws SecurityException {
        String path = new File("src/main/resources/EbayTaskConfig.json")
                .getAbsolutePath();
        return path;
    }
}
