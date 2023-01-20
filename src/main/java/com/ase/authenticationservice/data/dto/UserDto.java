package com.ase.authenticationservice.data.dto;

import com.ase.authenticationservice.data.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String email;
    private String password;
    private UserType userType;
}
