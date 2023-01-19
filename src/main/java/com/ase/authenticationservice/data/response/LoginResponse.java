package com.ase.authenticationservice.data.response;

import com.ase.authenticationservice.data.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String id;
    private UserType userType;
}
