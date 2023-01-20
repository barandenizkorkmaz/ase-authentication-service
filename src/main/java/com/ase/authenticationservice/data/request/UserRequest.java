package com.ase.authenticationservice.data.request;

import com.ase.authenticationservice.data.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    /*
        This request type has been used for register and update user endpoints.
     */
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please enter a valid email")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @NotNull
    private String password;

    @NotNull(message = "User type cannot be blank")
    private UserType userType;
}
