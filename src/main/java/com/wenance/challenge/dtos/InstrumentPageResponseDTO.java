package com.wenance.challenge.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstrumentPageResponseDTO {
    private String purchasePrice;
    private String sellingPrice;
    private String dateTime;
}
