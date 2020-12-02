package com.demo.sheep.config;

import com.demo.sheep.interceptor.UserInterceptor;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Resource
	private HttpMessageConverters httpMessageConverters;

	/**
	 * MappingJackson2HttpMessageConverter 实现了HttpMessageConverter 接口，
	 * httpMessageConverters.getConverters() 返回的对象里包含了MappingJackson2HttpMessageConverter
	 *
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
		return new MappingJackson2HttpMessageConverter(new JacksonMapper());
	}


	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.addAll(httpMessageConverters.getConverters());
	}

	@Bean
	public UserInterceptor userInterceptor(){
		return new UserInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> excludePaths = new ArrayList<>();
		//不去校验的uri
		excludePaths.add("/error");
		excludePaths.add("/manage/login");
		excludePaths.add("/manage/user/check");
		excludePaths.add("/manage/js/**");
		excludePaths.add("/manage/image/**");
		excludePaths.add("/manage/css/**");
		excludePaths.add("/manage/lib/**");
		excludePaths.add("/manage/json/**");
		excludePaths.add("/manage/page/**");
		excludePaths.add("/manage/login.html");
		excludePaths.add("/manage/main.html");
		//app
		excludePaths.add("/app/user/login");
		excludePaths.add("/app/verify/code/image");
		excludePaths.add("/app/verify/code/msg");

		registry.addInterceptor(userInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns(excludePaths)
				.order(2);
	}
}