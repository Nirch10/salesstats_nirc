package com.ebaytask.salesstatistics.config;

import com.google.gson.Gson;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.File;
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
        if(ebayTaskConfig == null)
        {
            try {
                ebayTaskConfig = Deserialize(getConfigPath());
//                ebayTaskConfig = Deserialize("/Users/sapirchodorov/Downloads/SalesStatistics/src/main/java/com/ebaytask/salesstatistics/config/EbayTaskConfig.json");
            } catch (IOException e) {
                e.printStackTrace();
                throw new NullPointerException();
            }

        }
    }

    public static void Serialize(EbayTaskConfig ebayTaskConfig, String filePath) throws IOException {
        Gson jsonParser = new Gson();
        EbayTaskConfigWrapper.ebayTaskConfig = ebayTaskConfig;
        String json = jsonParser.toJson(EbayTaskConfigWrapper.ebayTaskConfig);
        Serialize(json, filePath);
    }

    private static void Serialize(String data, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(data);
        fw.close();
    }
    public static EbayTaskConfig Deserialize(String configPath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(configPath)));
        Gson jsonParser = new Gson();
        ebayTaskConfig = jsonParser.fromJson(content, EbayTaskConfig.class);
        return ebayTaskConfig;
    }

    @Bean
    public EbayTaskConfig ebayTaskConfig(){
       if(ebayTaskConfig == null)
           return new EbayTaskConfig();
       return ebayTaskConfig;
    }

    private String getConfigPath(){
        String basePath = new File("").getAbsolutePath();
        System.out.println(basePath);
        String path = new File("src/main/resources/EbayTaskConfig.json")
                .getAbsolutePath();
        return path;
    }
}
