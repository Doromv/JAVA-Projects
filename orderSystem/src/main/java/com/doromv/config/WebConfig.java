package com.doromv.config;

import com.doromv.interceptor.EmployeeLoginInterceptor;
import com.doromv.interceptor.UserLoginInterceptor;
import com.doromv.utils.JacksonObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
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
        registry.addInterceptor(new UserLoginInterceptor())
                //添加拦截的路径
                .addPathPatterns(
                        "/front/page/add-order.html",
                        "/front/page/address.html",
                        "/front/page/address-edit.html",
                        "/front/page/no-wify.html",
                        "/front/page/order.html",
                        "/front/page/pay-success.html",
                        "/front/page/user.html",
                        "/front/index.html"
                );
        registry.addInterceptor(new EmployeeLoginInterceptor())
                //添加拦截的路径
                .addPathPatterns(
                        "/backend/page/category/list.html",
                        "/backend/page/combo/combo.html",
                        "/backend/page/demo/demo.html",
                        "/backend/page/food/food.html",
                        "/backend/page/member/member.html",
                        "/backend/page/order/order.html"
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
