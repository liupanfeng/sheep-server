package com.demo.sheep.manager;


import com.demo.sheep.constant.UserLoginTypeEnum;
import com.demo.sheep.pojo.table.User;
import com.demo.sheep.pojo.transfer.TokenValue;
import com.demo.sheep.pojo.view.UserDTO;
import com.demo.sheep.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class UserRedisManager {
	@Value("${web.user.token.expire.time}")
	private Integer webTokenExpireTime;
	@Value("${app.user.token.expire.time}")
	private Integer appTokenExpireTime;
	@Value("${verify.code.expire.time}")
	private Integer verificationCodeExpireTime;

	@Resource
	private RedisTemplate<String, User> redisTemplate;
	@Resource
	private RedisTemplate<String, TokenValue> tokenRedisTemplate;
	@Resource
	private RedisTemplate<String, String> stringRedisTemplate;

	//存token
	public void saveTokenValue(String token, TokenValue tokenValue) {
		Integer time;
		if(UserLoginTypeEnum.APP.equals(tokenValue.getLoginType())){
			time = appTokenExpireTime;
		}else{
			time = webTokenExpireTime;
		}
		tokenRedisTemplate.opsForValue().
				set(RedisUtil.tokenKeyOfString(token), tokenValue, time, TimeUnit.SECONDS);
	}

	//刷新token
	public void refreshToken(String token,TokenValue tokenValue) {
		Integer time;
		if(UserLoginTypeEnum.APP.equals(tokenValue.getLoginType())){
			time = appTokenExpireTime;
		}else{
			time = webTokenExpireTime;
		}
		tokenRedisTemplate.expire(RedisUtil.tokenKeyOfString(token), time, TimeUnit.SECONDS);
	}

	//取token
	public TokenValue getTokenValue(String token) {
		ValueOperations<String, TokenValue> valueOperations = tokenRedisTemplate.opsForValue();
		String tokenKey = RedisUtil.tokenKeyOfString(token);
		return valueOperations.get(tokenKey);
	}

	//删除token
	public void removeToken(String token) {
		String tokenKey = RedisUtil.tokenKeyOfString(token);
		tokenRedisTemplate.delete(tokenKey);
	}

	//存用户
	public void saveUser(UserDTO dto) {
		HashOperations<String, String, UserDTO> hashOperations = redisTemplate.opsForHash();
		hashOperations.put(RedisUtil.userKeyOfHash(), String.valueOf(dto.getUserId()), dto);
	}

	//取用户
	public UserDTO getUser(String userId) {
		HashOperations<String, String, UserDTO> ops = redisTemplate.opsForHash();
		return ops.get(RedisUtil.userKeyOfHash(), userId);
	}

	//存验证码
	public void saveVerifyCode(String value, String mode, String code) {
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		opsForValue.set(RedisUtil.verifyCodeKeyOfString( mode, value), code, verificationCodeExpireTime, TimeUnit.SECONDS);
	}

	//取验证码
	public String getVerifyCode(String value, String mode) {
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		return opsForValue.get(RedisUtil.verifyCodeKeyOfString( mode, value));
	}

	//取验证码的过期时间
	public Long getVerifyCodeExpireTime(String value, String mode) {
		return stringRedisTemplate.getExpire(RedisUtil.verifyCodeKeyOfString(mode, value), TimeUnit.SECONDS);
	}

}
