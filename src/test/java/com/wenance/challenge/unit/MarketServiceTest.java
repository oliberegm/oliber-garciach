package com.wenance.challenge.unit;

import com.wenance.challenge.Utils;
import com.wenance.challenge.convert.InstrumentConvert;
import com.wenance.challenge.dtos.AverageResponseDTO;
import com.wenance.challenge.dtos.InstrumentResponseDTO;
import com.wenance.challenge.model.Instrument;
import com.wenance.challenge.repository.InstrumentRepository;
import com.wenance.challenge.service.MarketService;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MarketServiceTest {
    @Autowired
    private MarketService marketService;
    @Autowired
    private InstrumentRepository instrumentRepository;

    private List<Instrument> instrumentObj =Arrays.asList(
            new Instrument( new ObjectId("60ccfe368bfcca213637dace"), "btc", "ARS",
                    "btcars", Double.valueOf(100), Double.valueOf(100), 1624360865050L),
            new Instrument( new ObjectId("60ccfe368bfcca213637ddce"), "btc", "ARS",
                    "btcars", Double.valueOf(200), Double.valueOf(200), 1624360865055L));

    @BeforeEach
    public void init(){
        instrumentRepository.saveAll(instrumentObj);
    }

    @Test
    public void priceTest() {

        String time ="2021-06-22T08:21:10.000Z";
        Mono<InstrumentResponseDTO> r = marketService.price(Utils.createTimestampString(time));
        StepVerifier.create(r)
                .expectNext(InstrumentConvert.responseToEntity(instrumentObj.get(1)))
                .verifyComplete();
    }

    @Test
    public void average() {
        String time1 ="2021-06-22T07:15:10.000Z";
        String time2 ="2021-06-22T09:25:59.000Z";
        Mono<AverageResponseDTO> average = marketService.average(Utils.createTimestampString(time1),
                Utils.createTimestampString(time2));
        StepVerifier.create(average)
                .expectNext(new AverageResponseDTO(150.00))
                .verifyComplete();

    }

    @Test
    public void history() {
        StepVerifier.create( marketService.history(Optional.empty(), Optional.empty(), 0, 5) )
                .expectNextCount(2L)
                .verifyComplete();
    }

}
