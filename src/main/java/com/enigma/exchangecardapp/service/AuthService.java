package com.enigma.exchangecardapp.service;


import com.enigma.exchangecardapp.model.request.AuthRequest;
import com.enigma.exchangecardapp.model.response.LoginResponse;
import com.enigma.exchangecardapp.model.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(AuthRequest request);

    RegisterResponse registerAdmin(AuthRequest request);

    LoginResponse login(AuthRequest authRequest);
}
