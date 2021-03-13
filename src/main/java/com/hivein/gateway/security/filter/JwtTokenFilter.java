package com.hivein.gateway.security.filter;

import com.hivein.gateway.service.TokenVerificationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private TokenVerificationService tokenVerificationService;

    public JwtTokenFilter(TokenVerificationService tokenVerificationService) {
        this.tokenVerificationService = tokenVerificationService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        String token = tokenVerificationService.extractTokenFromRequest((HttpServletRequest) req);
        if (token != null && tokenVerificationService.validateToken(token)) {
            //TODO TO FIGUE OUT HOW TO AUTHENTICATE OR SET AUTHENTICATION
            //SecurityContextHolder.getContext().setAuthentication();
        }
        chain.doFilter(req, res);
    }
}
