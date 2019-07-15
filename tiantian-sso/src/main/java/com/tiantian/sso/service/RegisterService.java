package com.tiantian.sso.service;

import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.pojo.TbUser;

public interface RegisterService {

    public TiantianResult checkData(String param, int type);
    
    public TiantianResult register(TbUser user);
}
