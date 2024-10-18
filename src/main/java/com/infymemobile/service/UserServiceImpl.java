package com.infymemobile.service;

import java.util.ArrayList;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infymemobile.dto.LoginDTO;
import com.infymemobile.dto.UserDTO;
import com.infymemobile.entity.User;
import com.infymemobile.exception.InfyMeMobileException;
import com.infymemobile.helper.Helper;
import com.infymemobile.repository.UserRepository;
import com.infymemobile.utility.ExceptionConstants;
import com.infymemobile.validator.LoginValidator;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private Helper helper;

    @Override
    public String createUser(UserDTO userDTO) throws InfyMeMobileException {
        // TODO Auto-generated method stub
        Optional<User> optional = repository.findByMobileNumber(userDTO.getMobileNumber());

        if(optional.isEmpty()) {

            User users = new User();
            users.setAccountHolderName(userDTO.getAccountHolderName());
            users.setCommunicationAddress(userDTO.getCommunicationAddress());
            users.setDateOfBirth(userDTO.getDateOfBirth());
            users.setEmail(userDTO.getEmail());
            users.setGender(userDTO.getGender());
            users.setMobileNumber(userDTO.getMobileNumber());
            users.setpan(userDTO.getpan());
            users.setPassword(userDTO.getPassword());
            //users.setUserId(userDTO.getUserId());
            users.setUserId(helper.generateUserID());
            User newUser = repository.save(users);
            return String.valueOf(newUser.getMobileNumber());
        }else {
            throw new InfyMeMobileException(ExceptionConstants.USER_ALREADY_PRESENT.toString());
        }



    }

    @Override
    public UserDTO getUserProfile(String userId) throws InfyMeMobileException {
        // TODO Auto-generated method stub
        Optional<User> optional = repository.findByUserId(userId);
        User user = optional.orElseThrow(() -> new InfyMeMobileException(ExceptionConstants.USERID_NOT_FOUND.toString()));

        UserDTO dto = new UserDTO();
        dto.setAccountHolderName(user.getAccountHolderName());
        dto.setCommunicationAddress(user.getCommunicationAddress());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setEmail(user.getEmail());
        dto.setGender(user.getGender());
        dto.setMobileNumber(user.getMobileNumber());
        dto.setpan(user.getpan());
        dto.setPassword(user.getPassword());
        dto.setUserId(user.getUserId());


        return dto;
    }

    @Override
    public List<UserDTO> showAllUsers() throws InfyMeMobileException {
        // TODO Auto-generated method stub
        Iterable<User> itr = repository.findAll();


        List<UserDTO> dtoList = new ArrayList<>();
        itr.forEach(user -> {
            UserDTO dto = new UserDTO();
            dto.setAccountHolderName(user.getAccountHolderName());
            dto.setCommunicationAddress(user.getCommunicationAddress());
            dto.setDateOfBirth(user.getDateOfBirth());
            dto.setEmail(user.getEmail());
            dto.setGender(user.getGender());
            dto.setMobileNumber(user.getMobileNumber());
            dto.setpan(user.getpan());
            dto.setPassword(user.getPassword());
            dto.setUserId(user.getUserId());
            dtoList.add(dto);

        });

        if(dtoList.isEmpty()) {
            throw new InfyMeMobileException(ExceptionConstants.NO_USERS_FOUND.toString());
        }
        return dtoList;
    }

    @Override
    public Boolean loginUser(LoginDTO loginDTO) throws InfyMeMobileException {
        // TODO Auto-generated method stub
        LoginValidator.validateLogin(loginDTO);

        Optional<User> optional = repository.findByMobileNumber(loginDTO.getMobileNumber());
        User user = optional.orElseThrow(() -> new InfyMeMobileException(ExceptionConstants.USER_NOT_FOUND.toString()));

        if(user.getPassword().matches(loginDTO.getPassword()))
            return true;
        else
            throw new InfyMeMobileException(ExceptionConstants.AUTHENTICATION_FAILED.toString());

    }

}
