package com.rest_api.RestApi.service;

import com.rest_api.RestApi.dto.UserAddDTO;
import com.rest_api.RestApi.dto.UserDTO;

import java.util.List;

public interface Services {
    public List<UserDTO> getAllUsers();
    public UserDTO getUserById(Long id);
    public UserDTO createUser(UserAddDTO user);
    public UserDTO updateUser(Long id, UserDTO user);
    public void deleteUser(Long id);
}
