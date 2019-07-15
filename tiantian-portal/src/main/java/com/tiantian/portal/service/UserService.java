package com.tiantian.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tiantian.pojo.TbUser;

public interface UserService {

    public TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response);
}
