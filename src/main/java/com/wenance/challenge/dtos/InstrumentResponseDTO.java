package com.wenance.challenge.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstrumentResponseDTO implements DTO{
    private String marketIdentifier;
    private Double purchasePrice;
    private Double sellingPrice;
    private String currencyDate;
}
