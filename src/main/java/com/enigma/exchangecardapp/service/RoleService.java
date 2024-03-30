package com.enigma.exchangecardapp.service;


import com.enigma.exchangecardapp.model.entity.Role;

public interface RoleService {
    Role getOrSave(Role role);
}
