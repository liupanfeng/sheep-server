package com.demo.sheep.service;


import com.demo.sheep.constant.CommonStateEnum;
import com.demo.sheep.constant.UserLoginTypeEnum;
import com.demo.sheep.dao.UserMapper;
import com.demo.sheep.manager.UserRedisManager;
import com.demo.sheep.pojo.table.User;
import com.demo.sheep.pojo.transfer.TokenValue;
import com.demo.sheep.pojo.view.UserDTO;
import com.demo.sheep.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    @Value("${verify.code.expire.time}")
    private Integer verifyCodeExpireTime;
    @Value("${verify.code.interval.time}")
    private Integer verifyCodeIntervalTime;

    @Resource
    private UserRedisManager userRedisManager;
    @Resource
    private UserMapper userMapper;

    public User getUserByUserName(String userName) {
        return userMapper.selectByUsername(userName);
    }

    public void updateUserLastDateById(int userId) {
        userMapper.updateUserLastDate(userId, new Date());
    }

    public String generateToken(User user, UserLoginTypeEnum loginType) {
        String token = CommonUtil.generateUUID();

        TokenValue tv = new TokenValue();
        tv.setUserId(user.getUserId());
        tv.setLoginType(loginType);

        userRedisManager.saveTokenValue(token, tv);
        UserDTO dto = this.buildUserDTO(user);
        userRedisManager.saveUser(dto);
        return token;
    }

    public Integer getUserIdByToken(String token) {
        TokenValue tokenValue = userRedisManager.getTokenValue(token);
        if (tokenValue == null) {
            return null;
        }
        //刷新token的时间
        userRedisManager.refreshToken(token, tokenValue);
        return tokenValue.getUserId();
    }


    public void removeToken(String token) {
        TokenValue tokenValue = userRedisManager.getTokenValue(token);
        if (tokenValue != null) {
            userRedisManager.removeToken(token);
        }
    }


    public User getUserByUserId(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public User getUserByPhone(String phone) {
        User user = userMapper.selectUserByPhone(phone);
        if(user == null){
            user = new User();
            user.setPhone(phone);
            user.setInitDate(new Date());
            user.setLastDate(new Date());
            user.setState(CommonStateEnum.NORMAL.code());
            user.setUsername(phone);
            user.setUuid(CommonUtil.generateUUID());
            userMapper.insert(user);
        }
        return user;
    }

    public boolean checkIfCanSendVerifyCode(String value, String mode) {
        //首先判断之前发的是否已经过期
        Long expireTime = userRedisManager.getVerifyCodeExpireTime(value, mode);
        //一分钟发一次
        return expireTime == null || expireTime <= 0 || expireTime >= verifyCodeExpireTime - verifyCodeIntervalTime;
    }

    public void saveVerifyCode(String value, String mode, String code) {
        //存储验证码
        userRedisManager.saveVerifyCode(value, mode, code);
    }


    public boolean checkVerifyCode(String value, String mode, String code) {
        String verificationCode = userRedisManager.getVerifyCode(value,mode);
        return Objects.equals(code, verificationCode);
    }

    /**
     * 通过 user构建userDTO
     */
    private UserDTO buildUserDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setNickname(user.getNickname());
        dto.setInitDate(user.getInitDate());
        dto.setLastDate(user.getLastDate());
        return dto;
    }
}
