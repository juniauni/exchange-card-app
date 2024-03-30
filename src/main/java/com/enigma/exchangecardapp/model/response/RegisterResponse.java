package com.enigma.exchangecardapp.model.response;

import com.enigma.exchangecardapp.constant.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String email;
    private List<ERole> role;
}
