package com.codecool.shop.util;
import org.mindrot.jbcrypt.BCrypt;

public class Util {

    public String hash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean verify(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }

}
