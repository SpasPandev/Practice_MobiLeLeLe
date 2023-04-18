package com.example.practice_mobilelele.service.Impl;

import com.example.practice_mobilelele.model.entity.User;
import com.example.practice_mobilelele.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;

public class ApplicationUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public ApplicationUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

         /* The purpose of this method is to map our user representation (User)
            to the user representation in the spring security world (UserDetails).
            The only thing that spring will provide to us is the username.
            The username will come from the HTML login form.  */

        return mapToUserDetails(
                userRepository.findByUsername(username)
                        .orElseThrow(() ->
                                new UsernameNotFoundException("UserEntity with username: " + username + " not found!")));
    }

    private static UserDetails mapToUserDetails(User user) {

        /*  GrantedAuthority is the representation of a user role in the spring world.
            SimpleGrantedAuthority is an implementation of GrantedAuthority
            which spring provide for our convenience.
            Our representation of role is (Role).   */
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().getRole().name()));

        /*  User is the spring implementation of UserDetails interface. */
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
