package com.hivein.gateway.security.filter;

import com.hivein.gateway.model.JwtUser;
import com.hivein.gateway.service.AuthService;
import com.hivein.gateway.service.TokenVerificationService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private TokenVerificationService tokenVerificationService;
    private AuthService authService;

    public JwtTokenFilter(TokenVerificationService tokenVerificationService, AuthService authService) {
        this.tokenVerificationService = tokenVerificationService;
        this.authService = authService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        String token = tokenVerificationService.extractTokenFromRequest((HttpServletRequest) req);
        if (token != null && tokenVerificationService.validateToken(token)) {
            UserDetails userDetails = authService.getUserDetails("DJonsi");
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities())
            );
        }
        chain.doFilter(req, res);
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey("secretulya").parseClaimsJws(token).getBody().getSubject();
    }
}
