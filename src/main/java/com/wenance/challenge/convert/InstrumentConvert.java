package com.wenance.challenge.convert;

import com.wenance.challenge.Utils;
import com.wenance.challenge.dtos.InstrumentDTO;
import com.wenance.challenge.dtos.InstrumentPageResponseDTO;
import com.wenance.challenge.dtos.InstrumentResponseDTO;
import com.wenance.challenge.model.Instrument;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TimeZone;


public class InstrumentConvert {

    public static Instrument fromEntrySet(Map.Entry<String, InstrumentDTO> value) {
        return InstrumentConvert.fromDTO(value.getValue());
    }

    public static Instrument fromDTO(InstrumentDTO to) {
        return Instrument.builder()
                .purchasePrice(Double.parseDouble(to.getPurchasePrice()))
                .sellingPrice( Double.parseDouble(to.getSellingPrice()))
                .bidCurrency(to.getBidCurrency())
                .currency(to.getCurrency())
                .marketIdentifier(to.getMarketIdentifier() )
                .time( Utils.createTimestamp() )
                .build();
    }


    public static InstrumentResponseDTO responseToEntity(Instrument instrument) {
        return new InstrumentResponseDTO(instrument.getMarketIdentifier(),
                instrument.getPurchasePrice(),
                instrument.getSellingPrice(),
                Utils.createTimestampString( Utils.createTimestampLong( instrument.getTime() ) ) );//.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    public static InstrumentPageResponseDTO instrumentPageResponseDTO(Instrument instrument) {
        return InstrumentPageResponseDTO.builder()
                .dateTime((LocalDateTime.ofInstant(Instant.ofEpochMilli(instrument.getTime()),
                        TimeZone.getDefault().toZoneId()))   .format(DateTimeFormatter.ISO_DATE_TIME) )
                .purchasePrice(instrument.getPurchasePrice().toString())
                .sellingPrice(instrument.getSellingPrice().toString())
                .build();
    }

}
