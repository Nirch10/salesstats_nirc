package com.ebaytask.salesstatistics.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EbayTaskConfig {

    private Integer AppHttpPort;
    private Integer SecondsToSaveTransaction;

    public EbayTaskConfig(int appHttpPort, int secondsToSaveTransaction){
        AppHttpPort = appHttpPort;
        SecondsToSaveTransaction = secondsToSaveTransaction;
    }
}
