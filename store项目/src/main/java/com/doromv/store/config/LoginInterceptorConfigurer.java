package com.doromv.store.config;

import com.doromv.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Doromv
 * @create 2022-03-31-15:25
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/users/login","/web/index.html","/districts/**",
                        "/web/register.html","/web/login.html",
                        "/web/product.html", "/users/reg",
                        "/bootstrap3/**","/css/**",
                        "/images/**","/js/**","/products/**");
    }
}
