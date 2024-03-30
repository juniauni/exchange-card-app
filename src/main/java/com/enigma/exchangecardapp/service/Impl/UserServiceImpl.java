package com.enigma.exchangecardapp.service.Impl;

import com.enigma.exchangecardapp.model.entity.AppUser;
import com.enigma.exchangecardapp.model.entity.Role;
import com.enigma.exchangecardapp.model.entity.User;
import com.enigma.exchangecardapp.repository.UserRepository;
import com.enigma.exchangecardapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public AppUser loadUserByUserId(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("invalid credential"));

        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(Role::getName).toList())
                .build();
    }

    public AppUser loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("invalid credential"));

        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(Role::getName).toList())
                .build();
    }
}
