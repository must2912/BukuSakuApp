package com.stematel.bukusaku.Service;

import com.orhanobut.hawk.Hawk;
import com.stematel.bukusaku.Model.User.UserResponse;

import java.util.List;

/**
 * Created by tofa-pc on 1/24/2018.
 */

public class LocalService {
    private static final String LOGIN_RESPONSE = "Key.LoginResponse";
    private static final String KERANJANG_RESPONSE = "Key.Keranjang";

    public static void saveLogin(UserResponse response){
        delLogin();
        Hawk.put(LOGIN_RESPONSE, response);
    }

    public static UserResponse getLogin() {
        return Hawk.get(LOGIN_RESPONSE);
    }

    public static void delLogin(){
        Hawk.delete(LOGIN_RESPONSE);
    }

}
