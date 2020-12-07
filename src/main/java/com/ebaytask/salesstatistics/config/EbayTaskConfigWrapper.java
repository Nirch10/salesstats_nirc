package com.ebaytask.salesstatistics.config;

import com.google.gson.Gson;
import lombok.Getter;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import java.io.*;
import java.nio.charset.StandardCharsets;


@Configuration
@ComponentScan("lib")
@Getter
public class EbayTaskConfigWrapper {

    public static EbayTaskConfig ebayTaskConfig;

    public EbayTaskConfigWrapper(){
        if(ebayTaskConfig == null) {
            try {
                ebayTaskConfig = Deserialize();
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
                ebayTaskConfig = Deserialize();
        }
        return ebayTaskConfig.getSecondsToSaveTransaction();
    }

    public static EbayTaskConfig Deserialize() throws IOException {
        final ClassLoader classLoader = PropertiesUtil.class.getClassLoader();
        final InputStream inputStream = classLoader.getResourceAsStream("EbayTaskConfig.json");
        String content = parseStream(inputStream);
        Gson jsonParser = new Gson();
        ebayTaskConfig = jsonParser.fromJson(content, EbayTaskConfig.class);
        return ebayTaskConfig;
    }

    private static String parseStream(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        int charsRead;
        while((charsRead = in.read(buffer, 0, buffer.length)) > 0) {
            out.append(buffer, 0, charsRead);
        }
        return out.toString();
    }

}
