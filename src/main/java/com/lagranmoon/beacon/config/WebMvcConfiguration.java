package com.lagranmoon.beacon.config;

import com.lagranmoon.beacon.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Lagranmoon
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .excludePathPatterns("/auth/**","/error");
    }



//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//
//        registry.addRedirectViewController("/doc/v2/api-docs", "/v2/api-docs?group=restful-api");
//        registry.addRedirectViewController("/doc/swagger-resources/configuration/ui","/swagger-resources/configuration/ui");
//        registry.addRedirectViewController("/doc/swagger-resources/configuration/security","/swagger-resources/configuration/security");
//        registry.addRedirectViewController("/doc/swagger-resources", "/swagger-resources");
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.
//                addResourceHandler("/doc/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
//        registry.
//                addResourceHandler("/doc/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
}
