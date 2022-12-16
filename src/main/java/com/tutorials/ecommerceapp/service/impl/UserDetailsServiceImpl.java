package com.tutorials.ecommerceapp.service.impl;

import com.tutorials.ecommerceapp.model.User;
import com.tutorials.ecommerceapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("No User " +
                        "Found with the Username: " + username)
        );

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), true,
                true, true, true,
                getAuthorities(user)
        );
    }


    private Collection<? extends GrantedAuthority> getAuthorities(User user){
        return user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                .collect(Collectors.toList());
    }
}
