package com.enigma.exchangecardapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRequest {
    private String customerId;
    private String oldCardId;
    private String newCardId;
}
