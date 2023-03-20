package com.example.hospitalreservation.config.security.filter;


import com.example.hospitalreservation.config.security.dto.CustomPrincipal;
import com.example.hospitalreservation.config.security.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    /**
     * 인증을 시도하는 메서드
     */
//    @SneakyThrows
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
////        ObjectMapper objectMapper = new ObjectMapper();
////        SignInDto loginDto = objectMapper.readValue(request.getInputStream(), SignInDto.class);
//
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        SignInDto loginDto = new SignInDto(username, password);
//
//        log.info("로그인을 시도합니다 : {}");
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.username(),loginDto.password());
//
//        log.info("Token: {}",authenticationToken);
//
//        HttpSession session = request.getSession();
//        session.setAttribute("sessionId",loginDto.username());
//
//        log.info("sessionId : {}",session.getAttribute("sessionId"));
//        // AuthenticationManager에게 인증 위임
//        return authenticationManager.authenticate(authenticationToken);
//
//    }


    /**
     * 인증에 성공할 경우 호출되는 메서드
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain,
                                            Authentication authResult) throws ServletException, IOException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal(); // Member 엔티티 객체 얻기

        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        log.info("authResult : {}", authResult.getPrincipal().toString());
        log.info("인증에 성공하였습니다 : {}", customUserDetails.username());

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("sessionId");

        log.info("sessionId : {}",username );

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                CustomPrincipal.of(customUserDetails.userId(), customUserDetails.username(),customUserDetails.name()), null, customUserDetails.authorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("authentication: {}", authentication);
        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }

}
