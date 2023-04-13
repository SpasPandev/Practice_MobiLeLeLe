package com.example.practice_mobilelele.service.Impl;

import com.example.practice_mobilelele.model.binding.UserRegisterBindingModel;
import com.example.practice_mobilelele.model.entity.User;
import com.example.practice_mobilelele.model.enums.RoleEnum;
import com.example.practice_mobilelele.repository.RoleRepository;
import com.example.practice_mobilelele.repository.UserRepository;
import com.example.practice_mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {

        User newUser = modelMapper.map(userRegisterBindingModel, User.class);
        newUser.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
        newUser.setRole(roleRepository.findByName(RoleEnum.User));

        userRepository.save(newUser);
    }
}
