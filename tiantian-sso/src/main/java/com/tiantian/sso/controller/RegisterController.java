package com.tiantian.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.common.pojo.TiantianResult;
import com.tiantian.common.utils.ExceptionUtil;
import com.tiantian.pojo.TbUser;
import com.tiantian.sso.service.RegisterService;

@Controller
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
        try {
            TiantianResult result = registerService.checkData(param, type);
            if (StringUtils.isNotBlank(callback)) {
                // 请求为jsonp调用，需要支持
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue; // jsonp返回
            }
            return result; // 正常的查询结果
        } catch (Exception e) {
            e.printStackTrace();
            return TiantianResult.build(500, ExceptionUtil.getStackTrace(e)); // 出错的结果
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public TiantianResult register(TbUser user) {
        try {
            TiantianResult result = registerService.register(user);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return TiantianResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

}
