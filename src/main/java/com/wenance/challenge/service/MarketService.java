package com.wenance.challenge.service;

import com.wenance.challenge.dtos.AverageResponseDTO;
import com.wenance.challenge.dtos.InstrumentDTO;
import com.wenance.challenge.dtos.InstrumentPageResponseDTO;
import com.wenance.challenge.dtos.InstrumentResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MarketService {

    Mono<InstrumentResponseDTO> price(LocalDateTime date);

    Mono<AverageResponseDTO> average(LocalDateTime start, LocalDateTime end);

    Flux<InstrumentPageResponseDTO> history(Optional<LocalDateTime> startDate, Optional<LocalDateTime> endDate,
                                            int page, int size);


}
