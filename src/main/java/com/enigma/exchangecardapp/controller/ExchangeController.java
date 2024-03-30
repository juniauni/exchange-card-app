package com.enigma.exchangecardapp.controller;

import com.enigma.exchangecardapp.model.entity.Exchange;
import com.enigma.exchangecardapp.model.request.ExchangeApproveRequest;
import com.enigma.exchangecardapp.model.request.ExchangeRequest;
import com.enigma.exchangecardapp.model.response.CommonResponse;
import com.enigma.exchangecardapp.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exchanges")
public class ExchangeController {
    private final ExchangeService exchangeService;
    @PostMapping
    private ResponseEntity<?> createNewExchange(@RequestBody ExchangeRequest exchangeRequest){
        Exchange exchange = exchangeService.create(exchangeRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created new exchange")
                        .data(exchange)
                        .build());
    }
    @PutMapping("/approve")
    public ResponseEntity<?> updateLoan(@RequestBody ExchangeApproveRequest approveRequest){
        Exchange exchange = exchangeService.approveOrReject(approveRequest);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully update or reject exchange!")
                .data(exchange)
                .build());
    }
}
