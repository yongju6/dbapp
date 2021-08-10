package com.korea.dbapp.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {

  // return이 true면 함수 진입, false면 진입 못함(함수 실행 안함)
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession session = request.getSession();
    if (session.getAttribute("principal") == null) {
      response.sendRedirect("/auth/loginForm");
      return false;
    }

    return true;
  } // end preHandle
}
