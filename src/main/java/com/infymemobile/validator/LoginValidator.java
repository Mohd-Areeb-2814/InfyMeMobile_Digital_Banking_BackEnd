package com.infymemobile.validator;

import com.infymemobile.dto.LoginDTO;
import com.infymemobile.exception.InfyMeMobileException;

public class LoginValidator {

    public static void validateLogin(LoginDTO login) throws InfyMeMobileException {

        isValidMobileNumber(login.getMobileNumber());
        isValidPassword(login.getPassword());
    }

    public static boolean isValidMobileNumber(Long mobileNo) throws InfyMeMobileException {

        String mobileNumber = String.valueOf(mobileNo);
        if(mobileNumber.matches("^(?!(\\d)\\1{9})\\d{10}$")) {
            return true;
        }
        else {
            throw new InfyMeMobileException("Mobile Number is Invalid");
        }

    }

    public static boolean isValidPassword(String password) throws InfyMeMobileException {

        if(password.matches("^(?=.*[a-zA-Z])(?=.*[0-9]).{5,15}$")) {
            return true;
        }
        else {
            throw new InfyMeMobileException("Password is Invalid");
        }
    }
}
