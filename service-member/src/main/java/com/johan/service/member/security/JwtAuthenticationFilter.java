package com.johan.service.member.security;

import com.johan.common.utils.JwtTokenUtil;
import com.johan.common.utils.RedisUtil;
import com.johan.service.member.entity.response.LoginRespondData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String accessToken = request.getHeader(jwtTokenUtil.getHeader());
        if (accessToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = null;
            LoginRespondData respondData = (LoginRespondData) redisUtil.get(accessToken);
            if (respondData != null) {
                userDetails = new WebUserDetail(respondData.getUser(), respondData.getRole());
            }
            if (userDetails == null) {
                String username = jwtTokenUtil.getUserName(accessToken);
                userDetails = userDetailsService.loadUserByUsername(username);
            }
            if (userDetails != null && validateToken(accessToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * ??????Token?????????
     *
     * @param token       Token
     * @param userDetails ????????????
     * @return Result
     */
    private boolean validateToken(String token, UserDetails userDetails) {
        String username = jwtTokenUtil.getUserName(token);
        return username.equals(userDetails.getUsername()) && !jwtTokenUtil.isExpiration(token);
    }

}
