package com.ase.authenticationservice.service;

import com.ase.authenticationservice.data.dto.UserDto;
import com.ase.authenticationservice.data.entity.User;
import com.ase.authenticationservice.data.request.UserRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ase.authenticationservice.data.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    @Autowired
    private IUserEntityService userEntityService;

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    @Override
    public UserDto createUser(UserRequest registerRequest) {
        System.out.println("UserService: createUser");
        User user = USER_MAPPER.createUser(registerRequest);
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getUserType());
        user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
        return USER_MAPPER.convertToUserDto(userEntityService.createUser(user));
    }

    @Override
    public UserDto updateUser(UserRequest updateRequest){
        User user = USER_MAPPER.createUser(updateRequest);
        user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
        return USER_MAPPER.convertToUserDto(userEntityService.updateUser(user));
    }

    @Override
    public List<UserDto> getUsers() {
        return USER_MAPPER.convertToUserDtoList(userEntityService.getUsers());
    }

    public void deleteUserById(String userId) {
        userEntityService.deleteUserById(userId);
    }

}
