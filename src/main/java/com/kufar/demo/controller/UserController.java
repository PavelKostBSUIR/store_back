package com.kufar.demo.controller;

import com.kufar.demo.dto.*;
import com.kufar.demo.entity.User;
import com.kufar.demo.mapper.UserMapper;
import com.kufar.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ActiveUserDTO findById(Principal principal, @PathVariable Long id) {
        return userMapper.userToActiveUserDTO(userService.findById(id, principal));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody @Valid CreateUserDTO createUserDTO) {
        return userService.create(userMapper.CreateUserDTOtoUser(createUserDTO));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody @Valid UpdateUserDTO updateUserDTO, Principal principal) {
        userService.update(id, userMapper.UpdateUserDTOtoUser(updateUserDTO), principal);
    }
}
