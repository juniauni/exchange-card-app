package com.enigma.exchangecardapp.service.Impl;

import com.enigma.exchangecardapp.model.entity.Card;
import com.enigma.exchangecardapp.repository.CardRepository;
import com.enigma.exchangecardapp.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public Card create(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card getById(String id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card update(Card card) {
        if (getById(card.getId()) != null)
            return cardRepository.save(card);
        return null;
    }

    @Override
    public void delete(String id) {
        if (getById(id) != null)
            cardRepository.deleteById(id);
    }
}
