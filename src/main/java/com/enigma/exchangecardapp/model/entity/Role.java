package com.enigma.exchangecardapp.model.entity;

import com.enigma.exchangecardapp.constant.ERole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private ERole name;
}
