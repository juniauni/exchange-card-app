package com.enigma.exchangecardapp.service.Impl;

import com.enigma.exchangecardapp.constant.ERole;
import com.enigma.exchangecardapp.model.entity.AppUser;
import com.enigma.exchangecardapp.model.entity.Customer;
import com.enigma.exchangecardapp.model.entity.Role;
import com.enigma.exchangecardapp.model.entity.User;
import com.enigma.exchangecardapp.model.request.AuthRequest;
import com.enigma.exchangecardapp.model.response.LoginResponse;
import com.enigma.exchangecardapp.model.response.RegisterResponse;
import com.enigma.exchangecardapp.repository.UserRepository;
import com.enigma.exchangecardapp.security.JwtUtil;
import com.enigma.exchangecardapp.service.AuthService;
import com.enigma.exchangecardapp.service.CustomerService;
import com.enigma.exchangecardapp.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;
    private final RoleService roleService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse registerCustomer(AuthRequest request) {
        try {

            Role role = Role.builder()
                    .name(ERole.ROLE_CUSTOMER)
                    .build();
            role = roleService.getOrSave(role);

            User user = User.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(List.of(role))
                    .build();
            userRepository.saveAndFlush(user);

            Customer customer = Customer.builder()
                    .user(user)
                    .build();
            customerService.create(customer);

            return RegisterResponse.builder()
                    .email(user.getEmail())
                    .role(user.getRoles().stream().map(Role::getName).toList())
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"user already exist");
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse registerAdmin(AuthRequest request) {
        try {
            Role admin = Role.builder().name(ERole.ROLE_ADMIN).build();
            Role staff = Role.builder().name(ERole.ROLE_STAFF).build();
            admin = roleService.getOrSave(admin);
            staff = roleService.getOrSave(staff);


            User user = User.builder()
                    .email(request.getEmail().toLowerCase())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(List.of(admin, staff))
                    .build();
            userRepository.saveAndFlush(user);

            return RegisterResponse.builder()
                    .email(user.getEmail())
                    .role(user.getRoles().stream().map(Role::getName).toList())
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"admin already exist");
        }
    }

    @Override
    public LoginResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail().toLowerCase(),
                authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);
        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRoles())
                .build();
    }
}
