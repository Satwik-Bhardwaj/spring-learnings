package com.learnings.jwtdemo.filter;

import com.learnings.jwtdemo.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil util;

    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. read token from auth header
        String token = request.getHeader("Authorization");

        if(token != null) {
            // do validation
            String username = util.getUsername(token);

            System.out.println("Token:: " + token);

            // username should not be empty, cont-auth must be empty
            if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null)  {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // validate token
                boolean isValid = util.validateToken(token, userDetails.getUsername());

                if(isValid) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // final object stored in SecurityContext with user details (un, pwd)
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }


        filterChain.doFilter(request,response);
    }
}
