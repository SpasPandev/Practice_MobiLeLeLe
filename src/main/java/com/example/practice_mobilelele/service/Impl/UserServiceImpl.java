package com.example.practice_mobilelele.service.Impl;

import com.example.practice_mobilelele.model.entity.User;
import com.example.practice_mobilelele.repository.UserRepository;
import com.example.practice_mobilelele.service.UserService;
import com.example.practice_mobilelele.util.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    @Override
    public boolean login(String username, String password) {

        boolean userExists = userRepository.existsByUsernameAndPassword(username, password);

        if (userExists) {

            User user = userRepository.findByUsername(username);
            currentUser.setId(user.getId());
            currentUser.setFirstName(user.getFirstName());
            currentUser.setLastName(user.getLastName());
            currentUser.setUsername(user.getUsername());
            currentUser.setRole(user.getRole());
        }

        return userExists;
    }

    @Override
    public void logout() {

        currentUser.setId(null);
        currentUser.setFirstName(null);
        currentUser.setLastName(null);
        currentUser.setUsername(null);
        currentUser.setRole(null);
    }
}
