package com.demo.sheep.controller.manage;

import com.demo.sheep.constant.CommonConstant;
import com.demo.sheep.constant.ResultCode;
import com.demo.sheep.constant.UserLoginTypeEnum;
import com.demo.sheep.pojo.table.User;
import com.demo.sheep.pojo.view.Result;
import com.demo.sheep.service.UserService;
import com.demo.sheep.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("manage")
public class MangeIndexController {

    private static final Logger log = LoggerFactory.getLogger(MangeIndexController.class);

    @Resource
    private UserService userService;

    /**
     *登录
     */
    @RequestMapping(value = "user/check",method = RequestMethod.POST)
    @ResponseBody
    public Result checkUser(String username, String password, HttpServletRequest request){
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        User user = userService.getUserByUserName(username);
        if(user == null){
            return Result.failure(ResultCode.USER_NOT_EXISTS);
        }
        //md5加密
        password = DigestUtils.md5DigestAsHex((password.trim() + CommonConstant.PASSWORD_SALT).getBytes());
        if(!password.equals(user.getPassword())){
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
        //登录成功，生成token，并存放到redis
        String token = userService.generateToken(user, UserLoginTypeEnum.WEB);
        //更新最近登录时间
        userService.updateUserLastDateById(user.getUserId());
        //返回前端需要的数据
        Map<String,String> map = new HashMap<>(2);
        map.put("token",token);
        map.put("nickname",user.getNickname());
        return Result.success(map);
    }

    /**
     *  退出完成后，去登录页
     */
    @RequestMapping(value = "user/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request){
        String token = CommonUtil.getTokenFromRequest(request);
        if(StringUtils.isNotBlank(token)){
            userService.removeToken(token);
        }
        return Result.success();
    }



}
