package com.example.practice_mobilelele.service.Impl;

import com.example.practice_mobilelele.repository.UserRepository;
import com.example.practice_mobilelele.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String username, String password) {

        return userRepository.existsByUsernameAndPassword(username, password);
    }
}
