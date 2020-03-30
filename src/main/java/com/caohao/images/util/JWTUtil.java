package com.caohao.images.util;

import com.caohao.images.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;
import java.util.Date;

public class JWTUtil {
    private static final String SUBJECT = "ImagesProject";
    private static final long EXPIRE = 1000*60*60*24*7;//一周
    private static final String APPSECRET = "caohao";
    private static Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    /**
     * 通过user构造一个token
     */
    public static String getTokenByUser(User user){
        if (user==null||user.getId()==null||user.getUserName()==null||user.getNickName()==null){
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("userName", user.getUserName())
                .claim("nickName", user.getNickName())
                .claim("tel", user.getTel())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();
        return token;
    }
    /**
     * 、解析token
     */
    public static Claims perfixToken(String token){
        final Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            String message = e.getMessage();
            logger.info(message);
        }
        return null;
    }
    /**
     * 验证是否过期的方法
     */
    public static boolean isBeforeExpriation(Date expriation){
        boolean before = expriation.before(new Date());
        return before;
    }
    /**
     * 判断是否为合格的token
     */
    public static boolean checkToken(Claims claims){
        if (claims==null){//解密失败才会返回null
            return false;
        }
        if (!claims.getExpiration().before(new Date())){//如果token过时也返回false
            return false;
        }
        if (claims.get("id")==null){//如果这个用户不存在
            return false;
        }
        return true;
    }
}
