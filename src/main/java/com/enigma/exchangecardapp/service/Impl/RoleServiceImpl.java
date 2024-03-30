package com.enigma.exchangecardapp.service.Impl;

import com.enigma.exchangecardapp.model.entity.Role;
import com.enigma.exchangecardapp.repository.RoleRepository;
import com.enigma.exchangecardapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public Role getOrSave(Role role) {
        Optional<Role> optionalRole = repository.findByName(role.getName());

        if (!optionalRole.isEmpty()){
            return optionalRole.get();
        }
        return repository.saveAndFlush(role);
    }
}
