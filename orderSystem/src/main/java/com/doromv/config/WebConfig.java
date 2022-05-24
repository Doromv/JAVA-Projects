package com.doromv.config;

import com.doromv.interceptor.LoginInterceptor;
import com.doromv.utils.JacksonObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Doromv
 * @create 2022-05-23-9:32
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //放行的路径
                .excludePathPatterns(
                        "/employee/login",
                        "/employee/logout",
                        "/backend/page/login/login.html",
                        "/backend/js/**",
                        "/backend/images/**",
                        "/backend/api/**",
                        "/backend/plugins/**",
                        "/backend/styles/**",
                        "/backend/favicon.ioc",
                        "/front/**",
                        "/backend/page/demo/upload.html",
                        "common/**"
                );
    }

    /**
     * 扩展消息转换器
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //设置自定义的转换器加入到转换器对象
        converter.setObjectMapper(new JacksonObjectMapper());
        WebMvcConfigurer.super.extendMessageConverters(converters);
        //将转换器对象加入到框架的转换器集合当中，并且将其索引更改为0，保证优先使用它
        converters.add(0,converter);
    }
}
