package com.enigma.exchangecardapp.repository;

import com.enigma.exchangecardapp.constant.ERole;
import com.enigma.exchangecardapp.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
