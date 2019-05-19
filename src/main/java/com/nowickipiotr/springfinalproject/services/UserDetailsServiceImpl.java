package com.nowickipiotr.springfinalproject.services;

import com.nowickipiotr.springfinalproject.models.User;
import com.nowickipiotr.springfinalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow(EntityNotFoundException::new);

        GrantedAuthority adminAuthority = new SimpleGrantedAuthority("ADMIN");
        GrantedAuthority userAuthority = new SimpleGrantedAuthority("USER");

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), Arrays.asList(adminAuthority));
        return userDetails;
    }
}
