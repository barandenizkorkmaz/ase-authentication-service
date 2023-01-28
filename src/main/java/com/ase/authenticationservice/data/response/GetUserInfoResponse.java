package com.ase.authenticationservice.data.response;

import com.ase.authenticationservice.data.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserInfoResponse {
    private UserType userType;
}
