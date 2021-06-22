package com.wenance.challenge.controller;

import com.wenance.challenge.dtos.AverageResponseDTO;
import com.wenance.challenge.dtos.InstrumentDTO;
import com.wenance.challenge.dtos.InstrumentPageResponseDTO;
import com.wenance.challenge.dtos.InstrumentResponseDTO;
import com.wenance.challenge.service.MarketService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/bitcoin")
public class MarketController {
    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }


    @GetMapping("/price/{date}")
    public Mono<InstrumentResponseDTO> priceBitcoin(@PathVariable(name = "date") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime date  ) {
        return marketService.price(date);
    }
    @GetMapping({"/average/{start}/{end}"})
    public Mono<AverageResponseDTO> averagePrice(@PathVariable(name = "start") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                 @PathVariable(name = "end") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return marketService.average(startDate, endDate);

    }
    @GetMapping(value = {"/history", "/history/{start}/{end}"} )
    public Flux<InstrumentPageResponseDTO> historyPrice(@PathVariable(name = "start") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> startDate,
                                                          @PathVariable(name = "end") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> endDate,
                                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "size", defaultValue = "10") int size) {
        return marketService.history(startDate, endDate, page, size);

    }
}
