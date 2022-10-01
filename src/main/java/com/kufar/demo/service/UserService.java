package com.kufar.demo.service;

import com.kufar.demo.entity.Role;
import com.kufar.demo.entity.User;
import com.kufar.demo.exception.ResourceNotFoundException;
import com.kufar.demo.repo.ProductRepository;
import com.kufar.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id, Principal principal) {
        User user = userRepository.findByLogin(principal.getName());
        if (user != null) {
            if (Objects.equals(id, user.getId())) {
                return user;
            } else {
                throw new BadCredentialsException("Permission denied");
            }
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public Long create(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Transactional
    public void update(Long id, User user, Principal principal) {
        User storedUser = findById(id, principal);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);
        user.setPassword(storedUser.getPassword());
        user.setLogin(storedUser.getLogin());
        user.setId(storedUser.getId());
        userRepository.save(user);
    }

}
