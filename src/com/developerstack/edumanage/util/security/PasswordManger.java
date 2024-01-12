package com.developerstack.edumanage.util.security;

import org.mindrot.BCrypt;

public class PasswordManger {
    public String encrypt(String rawPassword){
        return BCrypt.hashpw(rawPassword,BCrypt.gensalt(10));
    }

    public boolean checkPassowrd(String rawPassowrd, String hashPassword){
       return BCrypt.checkpw(rawPassowrd,hashPassword);
    }
}
