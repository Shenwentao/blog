package cn.xiaqileyu.blog.security;

import cn.xiaqileyu.blog.constant.CommonConstants;
import cn.xiaqileyu.blog.constant.JwtConstants;
import cn.xiaqileyu.blog.constant.ResultCode;
import cn.xiaqileyu.blog.domain.request.UserLoginRequest;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import cn.xiaqileyu.blog.domain.vo.UserLoginVO;
import cn.xiaqileyu.blog.domain.vo.UserVO;
import cn.xiaqileyu.blog.service.impl.UserServiceImpl;
import cn.xiaqileyu.blog.util.ApplicationContextUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，在这个方法里生成token。
 *
 * @author swt
 * @date 2020/6/24
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(JWTLoginFilter.class);

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {

        StringBuffer wholeStr = new StringBuffer();
        try {
            BufferedReader br = req.getReader();

            String str;
            while ((str = br.readLine()) != null) {
                wholeStr = wholeStr.append(str);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        UserLoginRequest loginRequest = JSON.parseObject(wholeStr.toString(), UserLoginRequest.class);

        res.setHeader(JwtConstants.CONTENT_TYPE_KEY, JwtConstants.CONTENT_TYPE_VALUE);
        PrintWriter printWriter;
        try {
            printWriter = res.getWriter();
        } catch (IOException e) {
            logger.error("getWriter|读取writer错误. e:{}", e);
            return null;
        }

        if (null == loginRequest) {
            printWriter.write(JSONObject.toJSONString(
                    ResultVO.errorResult(ResultCode.LOGIN_INFO_IS_EMPTY.getCode(),ResultCode.LOGIN_INFO_IS_EMPTY.getMsg()),
                    SerializerFeature.WriteMapNullValue));
            return null;
        }

        String userName = loginRequest.getLoginName();
        String password = loginRequest.getPassword();

        if (StringUtils.isEmpty(userName)) {
            printWriter.write(JSONObject.toJSONString(
                    ResultVO.errorResult(ResultCode.LOGIN_NAME_IS_EMPTY.getCode(),ResultCode.LOGIN_NAME_IS_EMPTY.getMsg()),
                    SerializerFeature.WriteMapNullValue));
            return null;
        }

        if (StringUtils.isEmpty(password)) {
            printWriter.write(JSONObject.toJSONString(
                    ResultVO.errorResult(ResultCode.LOGIN_PASSWORD_IS_EMPTY.getCode(),ResultCode.LOGIN_PASSWORD_IS_EMPTY.getMsg()),
                    SerializerFeature.WriteMapNullValue));
            return null;
        }

        Authentication authentication = null;

        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userName, password, new ArrayList<>()));
        } catch (AuthenticationException a) {
            logger.info("authenticate|用户登录失败");
            if (ResultCode.PASSWORD_IS_INCORRECT.getMsg().equals(a.getMessage())) {
                printWriter.write(JSONObject.toJSONString(
                        ResultVO.errorResult(ResultCode.PASSWORD_IS_INCORRECT.getCode(),ResultCode.PASSWORD_IS_INCORRECT.getMsg()),
                        SerializerFeature.WriteMapNullValue));
            }
            if (ResultCode.USER_NOT_EXIST.getMsg().equals(a.getMessage())) {
                printWriter.write(JSONObject.toJSONString(
                        ResultVO.errorResult(ResultCode.USER_NOT_EXIST.getCode(),ResultCode.USER_NOT_EXIST.getMsg()),
                        SerializerFeature.WriteMapNullValue));
            }
        }

        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException {

        UserServiceImpl userService = (UserServiceImpl) ApplicationContextUtil
                .getBean("userServiceImpl");

        // 获取用户信息
        ResultVO<UserVO> resultVO = userService.queryUserByName(auth.getName());

        UserLoginVO loginVO = new UserLoginVO();

        if (null != resultVO.getData()){
            loginVO.setUser(resultVO.getData());
        }

        String token = Jwts.builder()
                // Subject 设置用户名
                .setSubject(auth.getName())
                // 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + CommonConstants.MILLISECONDS_PER_DAY))
                // 签名算法
                .signWith(SignatureAlgorithm.HS512, JwtConstants.SIGNING_KEY)
                .compact();

        loginVO.setToken(JwtConstants.TOKEN_FLAG + token);

        res.addHeader(JwtConstants.HEADER_AUTH_KEY, JwtConstants.TOKEN_FLAG + token);
        res.setHeader(JwtConstants.CONTENT_TYPE_KEY, JwtConstants.CONTENT_TYPE_VALUE);
        res.getWriter()
                .write(JSONObject.toJSONString(ResultVO.successResult(loginVO), SerializerFeature.WriteMapNullValue));

    }

}
