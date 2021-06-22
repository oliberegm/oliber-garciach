package com.wenance.challenge.service;

import com.wenance.challenge.convert.InstrumentConvert;
import com.wenance.challenge.dtos.InstrumentDTO;
import com.wenance.challenge.dtos.TickersDTO;
import com.wenance.challenge.model.Instrument;
import com.wenance.challenge.repository.InstrumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class LoadDataServiceImpl implements LoadDataService{
    private final String URL_DATA = "https://be.buenbit.com/api/market/tickers/";
    private final InstrumentRepository instrumentRepository;
    private final WebClient webClient;

    @Autowired
    public LoadDataServiceImpl(InstrumentRepository instrumentRepository, WebClient webClient) {
        this.instrumentRepository = instrumentRepository;
        this.webClient = webClient;
    }

    /**
     * Aqui nos permite cargar la datas del server
     */
    @Override
    public void loadPriceBitcoin() {
        webClient.get()
                .uri(URL_DATA)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TickersDTO.class)
                .subscribe(success -> processSuccess(success), throwable -> processError(throwable));

    }

    private void processError(Throwable throwable) {
        log.info(throwable.getMessage());
        throwable.printStackTrace();
    }

    private void processSuccess(TickersDTO data) {
        log.info("load data {}",data);
        List<Instrument> listData = data.getObject().entrySet().stream()
                .map(InstrumentConvert::fromEntrySet)
                .collect(Collectors.toList());
        instrumentRepository.saveAll(listData);
    }

}
