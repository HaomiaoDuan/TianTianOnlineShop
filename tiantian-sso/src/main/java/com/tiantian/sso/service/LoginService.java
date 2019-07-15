package com.tiantian.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tiantian.common.pojo.TiantianResult;

public interface LoginService {

    public TiantianResult login(String username, String password, HttpServletRequest request,
            HttpServletResponse response) ;
    
    public TiantianResult getUserByToken(String token);
}
