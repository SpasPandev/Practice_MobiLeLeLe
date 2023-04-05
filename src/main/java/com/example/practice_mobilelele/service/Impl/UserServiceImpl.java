package com.example.practice_mobilelele.service.Impl;

import com.example.practice_mobilelele.model.binding.UserRegisterBindingModel;
import com.example.practice_mobilelele.model.entity.User;
import com.example.practice_mobilelele.model.enums.RoleEnum;
import com.example.practice_mobilelele.repository.RoleRepository;
import com.example.practice_mobilelele.repository.UserRepository;
import com.example.practice_mobilelele.service.UserService;
import com.example.practice_mobilelele.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, CurrentUser currentUser, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean login(String username, String password) {

        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {

            currentUser.setId(user.getId());
            currentUser.setFirstName(user.getFirstName());
            currentUser.setLastName(user.getLastName());
            currentUser.setUsername(user.getUsername());
            currentUser.setRole(user.getRole());

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void logout() {

        currentUser.setId(null);
        currentUser.setFirstName(null);
        currentUser.setLastName(null);
        currentUser.setUsername(null);
        currentUser.setRole(null);
    }

    @Override
    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {

        User newUser = modelMapper.map(userRegisterBindingModel, User.class);
        newUser.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
        newUser.setRole(roleRepository.findByName(RoleEnum.User));

        userRepository.save(newUser);
    }
}
