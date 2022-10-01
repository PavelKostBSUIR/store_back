package com.kufar.demo.mapper;

import com.kufar.demo.dto.*;
import com.kufar.demo.entity.Product;
import com.kufar.demo.entity.User;
import org.hibernate.sql.Update;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);

    ActiveUserDTO userToActiveUserDTO(User user);

    User UpdateUserDTOtoUser(UpdateUserDTO updateUserDTO);

    User CreateUserDTOtoUser(CreateUserDTO createUserDTO);
}
