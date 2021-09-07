package com.johan.common.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

@Component
@PropertySource("classpath:jwt.properties")
public class JwtTokenUtil {

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.iss}")
    private String iss;

    @Value("${jwt.expiration}")
    private int expiration;

    private static final String ROLE = "role";

    private Logger logger = Logger.getLogger("Text");

    /**
     * 创建Token
     *
     * @param username 用户名
     * @param role     角色
     * @return Result
     */
    public String createToken(String username, String role) {
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE, role);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secret)
                .setClaims(map)
                .setIssuer(iss)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * 获取用户名
     *
     * @param token Token
     * @return result
     */
    public String getUserName(String token) {
        String username;
        try {
            username = getTokenBody(token).getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 获取用户角色
     *
     * @param token Token
     * @return result
     */
    public String getUserRole(String token) {
        return (String) getTokenBody(token).get(ROLE);
    }

    /**
     * 获取自定义Map
     *
     * @param token Token
     * @return result
     */
    private Claims getTokenBody(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 是否已过期
     *
     * @param token Token
     * @return result
     */
    public boolean isExpiration(String token) {
        try {
            return getTokenBody(token).getExpiration().before(new Date());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    /**
     * 获取header的token字段
     *
     * @return token字段
     */
    public String getHeader() {
        return header;
    }

}
