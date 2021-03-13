package com.hivein.gateway.security.filter;

import com.hivein.gateway.service.ResourceService;
import com.hivein.gateway.service.TokenVerificationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private ResourceService resourceService;

    public JwtTokenFilter(TokenVerificationService tokenVerificationService, ResourceService resourceService) {
        this.tokenVerificationService = tokenVerificationService;
        this.resourceService = resourceService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        String token = tokenVerificationService.extractTokenFromRequest((HttpServletRequest) req);
        if (token != null && tokenVerificationService.validateToken(token)) {
            UserDetails userDetails = resourceService.getUserDetails(tokenVerificationService.getUsername(token));
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities())
            );
        }
        chain.doFilter(req, res);
    }
}
