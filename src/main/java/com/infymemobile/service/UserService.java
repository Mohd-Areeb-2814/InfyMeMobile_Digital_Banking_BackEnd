package com.infymemobile.service;

import java.util.List;


import com.infymemobile.dto.LoginDTO;
import com.infymemobile.dto.UserDTO;
import com.infymemobile.exception.InfyMeMobileException;

public interface UserService {

    String createUser(UserDTO userDTO) throws InfyMeMobileException;
    Boolean loginUser(LoginDTO loginDTO) throws InfyMeMobileException;
    UserDTO getUserProfile(String userId) throws InfyMeMobileException;
    List<UserDTO> showAllUsers() throws InfyMeMobileException;

}
