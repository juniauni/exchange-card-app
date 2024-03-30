package com.enigma.exchangecardapp.service.Impl;

import com.enigma.exchangecardapp.model.entity.AppUser;
import com.enigma.exchangecardapp.model.entity.Card;
import com.enigma.exchangecardapp.model.entity.Customer;
import com.enigma.exchangecardapp.model.entity.Exchange;
import com.enigma.exchangecardapp.model.request.ExchangeApproveRequest;
import com.enigma.exchangecardapp.model.request.ExchangeRequest;
import com.enigma.exchangecardapp.repository.CustomerRepository;
import com.enigma.exchangecardapp.repository.ExchangeRepository;
import com.enigma.exchangecardapp.security.JwtUtil;
import com.enigma.exchangecardapp.service.CardService;
import com.enigma.exchangecardapp.service.CustomerService;
import com.enigma.exchangecardapp.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final CustomerServiceImpl customerService;
    private final CardServiceImpl cardService;


    @Override
    public Exchange create(ExchangeRequest request) {
        Customer customer = customerService.getById(request.getCustomerId());
        Card oldCard = cardService.getById(request.getOldCardId());
        Card newCard = cardService.getById(request.getNewCardId());

        Exchange exchange = Exchange.builder()
                .customer(customer)
                .oldCard(oldCard)
                .newCard(newCard)
                .createdAt(LocalDateTime.now())
                .build();
        exchangeRepository.saveAndFlush(exchange);
        return exchange;
    }

    @Override
    public Exchange approveOrReject(ExchangeApproveRequest approveRequest) {
        String exchangeId = approveRequest.getExchangeId();
        Exchange existExchange = exchangeRepository.findById(exchangeId).orElse(null);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((AppUser) authentication.getPrincipal()).getEmail();
        if (existExchange != null) {
            existExchange.setApprovedAt(LocalDateTime.now());
            existExchange.setApprovalStatus(true);
            existExchange.setApprovedBy(email);
            existExchange.setUpdatedAt(LocalDateTime.now());

            Exchange exchange = exchangeRepository.save(existExchange);
            return exchange;
        }
        return null;
    }
}
