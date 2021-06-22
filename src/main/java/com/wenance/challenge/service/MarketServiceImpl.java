package com.wenance.challenge.service;

import com.wenance.challenge.Utils;
import com.wenance.challenge.convert.InstrumentConvert;
import com.wenance.challenge.dtos.AverageResponseDTO;
import com.wenance.challenge.dtos.InstrumentPageResponseDTO;
import com.wenance.challenge.dtos.InstrumentResponseDTO;
import com.wenance.challenge.model.Instrument;
import com.wenance.challenge.repository.InstrumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
@Slf4j
public class MarketServiceImpl implements MarketService{
    private final InstrumentRepository instrumentRepository;
    private final String bitcoinMark = "btcars";

    public MarketServiceImpl(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    @Override
    public Mono<InstrumentResponseDTO> price(LocalDateTime date) {
        InstrumentResponseDTO responseDTO = instrumentRepository.findByTimeBetween( Utils.createTimestamp(date)-5000, Utils.createTimestamp(date)).stream()
                .sorted((s1, s2) -> { return s1.getTime() > s2.getTime() ? -1 : 1; } )
                .filter(f -> f.getMarketIdentifier().equals(bitcoinMark))
                .map(InstrumentConvert::responseToEntity)
                .findFirst()
                .orElse(new InstrumentResponseDTO());
        return Mono.just(responseDTO);
    }

    @Override
    public Mono<AverageResponseDTO> average(LocalDateTime startDate, LocalDateTime endDate) {
        AverageResponseDTO responseDTO =
                new AverageResponseDTO(instrumentRepository.findByTimeBetween(Utils.createTimestamp(startDate), Utils.createTimestamp(endDate)).stream()
                        .filter(f -> f.getMarketIdentifier().equals(bitcoinMark))
                        .mapToDouble(Instrument::getSellingPrice)
                        .average()
                        .orElse(0));
        return Mono.just(responseDTO);
    }

    @Override
    public Flux<InstrumentPageResponseDTO> history(Optional<LocalDateTime> startDate, Optional<LocalDateTime> endDate,
                                                   int page, int size) {
        Pageable p = Pageable.ofSize(size).withPage(page);

        if (startDate.isPresent() && endDate.isPresent()) {
            return Flux.fromStream(instrumentRepository.findByTimeBetween( Utils.createTimestamp(startDate.get()), Utils.createTimestamp(endDate.get())).stream()
                    .sorted((s1, s2) -> { return s1.getTime() < s2.getTime() ? -1 : 1; } )
                    .filter(f -> f.getMarketIdentifier().equals(bitcoinMark))
                    .skip(page).limit(size)
                    .map(InstrumentConvert::instrumentPageResponseDTO));
        } else {
            return Flux.fromStream(instrumentRepository.findAll().stream()
                    .filter(f -> f.getMarketIdentifier().equals(bitcoinMark))
                    .skip(page).limit(size)
                    .map(InstrumentConvert::instrumentPageResponseDTO));
        }
    }


}
