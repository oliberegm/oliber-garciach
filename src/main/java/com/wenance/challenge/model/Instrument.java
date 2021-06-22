package com.wenance.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "instrument")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Instrument {
    @Id
    private ObjectId _id;
    private String bidCurrency;
    private String currency;
    private String marketIdentifier;
    private Double purchasePrice;
    private Double sellingPrice;
    private Long time;
}
