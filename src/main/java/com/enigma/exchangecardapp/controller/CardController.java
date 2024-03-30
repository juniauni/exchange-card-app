package com.enigma.exchangecardapp.controller;

import com.enigma.exchangecardapp.constant.AppPath;
import com.enigma.exchangecardapp.model.entity.Card;
import com.enigma.exchangecardapp.model.response.CommonResponse;
import com.enigma.exchangecardapp.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.CARD)
public class CardController {
    private final CardService cardService;
    @PostMapping
    public ResponseEntity<?> createCard(@RequestBody Card card) {
        Card cardResponse = cardService.create(card);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.<Card>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully created new card!")
                .data(cardResponse)
                .build());
    }

    @GetMapping
    public ResponseEntity<?> getAllCard() {
        List<Card> cardList = cardService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully fetch card!")
                .data(cardList)
                .build());
    }

    @GetMapping(AppPath.ID)
    public  ResponseEntity<?> getCardById(@PathVariable String id){
        Card cardResponse = cardService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully fetch card by id!")
                        .data(cardResponse)
                .build());
    }

    @PutMapping
    public ResponseEntity<?> updateCard(@RequestBody Card card) {
        Card cardResponse = cardService.update(card);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully update card!")
                .data(cardResponse)
                .build());
    }

    @DeleteMapping(AppPath.ID)
    public  ResponseEntity<?> deleteCardById(@PathVariable String id){
        cardService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully delete card by id!")
                .data(null)
                .build());
    }
}
