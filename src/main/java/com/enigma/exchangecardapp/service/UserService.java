package com.enigma.exchangecardapp.service;


import com.enigma.exchangecardapp.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
}
