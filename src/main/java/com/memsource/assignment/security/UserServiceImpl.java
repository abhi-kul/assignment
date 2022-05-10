package com.memsource.assignment.security;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.memsource.assignment.model.User;
import com.memsource.assignment.repository.UserRepository;

public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByName(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new UserPrincipal(user);
    }
}
