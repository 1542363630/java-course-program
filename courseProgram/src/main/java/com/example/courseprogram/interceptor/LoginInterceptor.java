package com.example.courseprogram.interceptor;

import com.example.courseprogram.model.DO.User;
import com.example.courseprogram.repository.UserRepository;
import com.example.courseprogram.utils.JwtUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.Objects;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    UserRepository userMapper;

    //仅管理员能访问的请求
    private final String[] FOR_ADMIN = {
            "/check"
    };

    public static LoginInterceptor loginInterceptor;

    @PostConstruct
    public void init(){
        loginInterceptor=this;
    }

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object Handle) {
        String token = request.getHeader("token");
        if (token == null) {
            throw new RuntimeException("未携带token");
        }
        if (!JwtUtil.verify(token)) {
            throw new RuntimeException("token无效");
        }
        String nickName = JwtUtil.getClaimsByToken(token).getSubject();
        User user = loginInterceptor.userMapper.findUserByUserName(nickName);
        if (user == null) {
            throw new RuntimeException("用户不存在,请重新登录");
        } else {
            String path = request.getServletPath();

            if (Arrays.stream(FOR_ADMIN).anyMatch(path::startsWith)) {
                if(!Objects.equals(loginInterceptor.userMapper.findUserTypeByName(nickName), "ADMIN")) throw new RuntimeException("no authority");
            }
        }
        return true;
    }

}


