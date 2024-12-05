package cn.xiaqileyu.blog.security;

import cn.xiaqileyu.blog.constant.JwtConstants;
import cn.xiaqileyu.blog.constant.ResultCode;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 *
 * @author swt
 * @date 2020/6/24
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(JwtConstants.HEADER_AUTH_KEY);

        if (header == null || !header.startsWith(JwtConstants.TOKEN_FLAG)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication;
        try {
            authentication = getAuthentication(request);
        } catch (ExpiredJwtException e) {
            logger.error("getAuthentication|token失效，message:{}", e);
            response.setStatus(ResultCode.TOKEN_FAILURE.getCode());
            response.setHeader(JwtConstants.CONTENT_TYPE_KEY, JwtConstants.CONTENT_TYPE_VALUE);
            response.getWriter()
                    .write(JSONObject.toJSONString(
                            ResultVO.errorResult(ResultCode.TOKEN_FAILURE.getCode(),ResultCode.TOKEN_FAILURE.getMsg()),
                            SerializerFeature.WriteMapNullValue));
            return;
        }
        // 如果需要用户名称，可以在SecurityContextHolder.getContext().getAuthentication中得到
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request)
            throws ExpiredJwtException {
        String token = request.getHeader(JwtConstants.HEADER_AUTH_KEY);
        if (token != null) {

            String user = Jwts.parser()
                    .setSigningKey(JwtConstants.SIGNING_KEY)
                    .parseClaimsJws(token.replace(JwtConstants.TOKEN_FLAG, ""))
                    .getBody().getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }

}
