package com.demo.sheep.interceptor;

import com.alibaba.fastjson.JSON;

import com.demo.sheep.constant.CommonConstant;
import com.demo.sheep.constant.ResultCode;
import com.demo.sheep.pojo.view.Result;
import com.demo.sheep.service.UserService;
import com.demo.sheep.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 把user对象放入session中
 */
public class UserInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(UserInterceptor.class);

	@Resource
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = request.getHeader(CommonConstant.TOKEN_KEY);
		log.info("请求的api --> {}",request.getRequestURI());
		if(StringUtils.isBlank(token)){
			log.info("token为空");
			returnFormatJson(response,JSON.toJSONString(Result.failure(ResultCode.TOKEN_IS_INVALID)));
			return false;
		}else{
			log.info("token:{}",token);
			Integer userId = userService.getUserIdByToken(token);
			if(userId == null){
				log.info("无效的token");
				returnFormatJson(response,JSON.toJSONString(Result.failure(ResultCode.TOKEN_IS_INVALID)));
				return false;
			}
			CommonUtil.putUserIdInRequest(request,userId);
		}
		return true;
	}

	/**
	 * 返回json类型的数据
	 */
	private void returnFormatJson(HttpServletResponse response,String json) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.write(json);
	}
}
