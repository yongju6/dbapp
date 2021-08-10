package com.korea.dbapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// IoC에 등록시킴 -> 메모리에 뜸
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  // 서버 실행시키면 자동으로 실행되고 SessionInterceptor가 자동으로 동작함
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 상세보기 / 목록보기 할 때에도 prehandle 해서 session 검사를 함
    registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/post/**").addPathPatterns("/user/**");
  }
}
