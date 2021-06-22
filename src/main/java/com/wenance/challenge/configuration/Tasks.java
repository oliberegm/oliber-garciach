package com.wenance.challenge.configuration;

import com.wenance.challenge.service.LoadDataService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Tasks {
    private final LoadDataService loadDataService;

    public Tasks(LoadDataService loadDataService) {
        this.loadDataService = loadDataService;
    }

    @Async("processExecutor")
    @Scheduled(fixedDelay = 1000 * 10 )
    public void loadDataServer() {
        loadDataService.loadPriceBitcoin();
    }
}
