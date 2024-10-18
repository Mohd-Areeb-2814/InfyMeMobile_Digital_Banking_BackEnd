package com.infymemobile.api;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infymemobile.dto.LoginDTO;
import com.infymemobile.dto.UserDTO;
import com.infymemobile.exception.InfyMeMobileException;
import com.infymemobile.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(value = "userService")
@Validated
public class UserApi {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/users")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) throws InfyMeMobileException{
        String res = userService.createUser(userDTO);
        return new ResponseEntity<String>(res, HttpStatus.CREATED);
    }

    @PostMapping(value = "/users/login")
    public ResponseEntity<Boolean> loginUser(@RequestBody LoginDTO loginDTO) throws InfyMeMobileException{
        Boolean res = userService.loginUser(loginDTO);
        return new ResponseEntity<Boolean>(res, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable String userId) throws InfyMeMobileException{
        UserDTO dto = userService.getUserProfile(userId);
        return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/users/all")
    public ResponseEntity<List<UserDTO>> showAllUsers() throws InfyMeMobileException{
        List<UserDTO> list = userService.showAllUsers();
        return new ResponseEntity<List<UserDTO>>(list, HttpStatus.OK);
    }
}
