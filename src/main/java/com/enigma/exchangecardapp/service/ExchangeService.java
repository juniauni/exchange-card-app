package com.enigma.exchangecardapp.service;

import com.enigma.exchangecardapp.model.entity.Exchange;
import com.enigma.exchangecardapp.model.request.ExchangeApproveRequest;
import com.enigma.exchangecardapp.model.request.ExchangeRequest;

import java.util.List;

public interface ExchangeService {
    Exchange create(ExchangeRequest request);
    Exchange approveOrReject(ExchangeApproveRequest approveRequest);
}
