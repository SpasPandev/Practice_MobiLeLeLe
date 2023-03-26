package com.example.practice_mobilelele.service;

import com.example.practice_mobilelele.model.binding.UserRegisterBindingModel;

public interface UserService {

    boolean login(String username, String password);

    void logout();

    void registerUser(UserRegisterBindingModel userRegisterBindingModel);
}
