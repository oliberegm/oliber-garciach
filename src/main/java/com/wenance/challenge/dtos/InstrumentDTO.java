package com.wenance.challenge.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InstrumentDTO implements DTO{
    @JsonProperty("bid_currency")
    private String bidCurrency;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("market_identifier")
    private String marketIdentifier;
    @JsonProperty("purchase_price")
    private String purchasePrice;
    @JsonProperty("selling_price")
    private String sellingPrice;
}
