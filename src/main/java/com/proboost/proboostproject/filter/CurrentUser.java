package com.proboost.proboostproject.filter;

import com.proboost.proboostproject.Modules.User;
import com.proboost.proboostproject.Respositories.UserRepo;
import lombok.Data;

@Data
public class CurrentUser {
    public static String  username = "" ;
    private static UserRepo userRepo ;

    public static User currentuser(){
        return userRepo.findByEmail(username).get() ;
    }
}
