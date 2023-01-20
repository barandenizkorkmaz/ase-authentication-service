package com.ase.authenticationservice.data.mapper;

import com.ase.authenticationservice.data.dto.UserDto;
import com.ase.authenticationservice.data.entity.User;
import com.ase.authenticationservice.data.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto convertToUserDto(User user);
    List<UserDto> convertToUserDtoList(List<User> users);

    User createUser(UserRequest userRequest);
    void updateUser(@MappingTarget User user, UserRequest userRequest);

    //User createUser(RegisterRequest registerRequest);
    //User createUser(UpdateRequest updateRequest);
}
