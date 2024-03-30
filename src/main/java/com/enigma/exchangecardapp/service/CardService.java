package com.enigma.exchangecardapp.service;

import com.enigma.exchangecardapp.model.entity.Card;

import java.util.List;

public interface CardService {
    Card create(Card card);
    Card getById(String id);
    List<Card> getAll();
    Card update(Card card);
    void delete(String id);
}
