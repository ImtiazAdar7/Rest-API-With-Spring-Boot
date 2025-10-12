package com.rest_api.RestApi.service.implementation;

import com.rest_api.RestApi.dto.UserAddDTO;
import com.rest_api.RestApi.dto.UserDTO;
import com.rest_api.RestApi.entity.User;
import com.rest_api.RestApi.repository.UserRepository;
import com.rest_api.RestApi.service.Services;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceImplementation implements Services {
//    @Autowired
    private final UserRepository userRepository;
//    @Autowired
    private final ModelMapper modelMapper;
    public ServiceImplementation(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(user->new UserDTO(user.getId(), user.getName(), user.getEmail())).toList();
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Id!"));
        UserDTO newUserDTO = modelMapper.map(user, UserDTO.class);
        return newUserDTO;
    }

    @Override
    public UserDTO createUser(UserAddDTO user) {
        User u = modelMapper.map(user, User.class);
        User dto = userRepository.save(u);
        return modelMapper.map(dto, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO user) {
        User u = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Id!"));
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        User saved =  userRepository.save(u);
        return modelMapper.map(saved, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        User gonnabe = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Id!"));
        userRepository.delete(gonnabe);
    }
}
