package com.ebaytask.salesstatistics.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class EbayTaskConfig {
    private Integer AppHttpPort;
    private Integer SecondsToSaveTransaction;
}
