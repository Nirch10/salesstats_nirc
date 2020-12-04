package com.ebaytask.salesstatistics.config;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EbayTaskConfigWrapper {

    public static EbayTaskConfig ebayTaskConfig;

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
}
