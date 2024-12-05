package cn.xiaqileyu.blog.security;

import cn.xiaqileyu.blog.constant.JwtConstants;
import cn.xiaqileyu.blog.constant.ResultCode;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功处理类
 *
 * @author swt
 * @date 2020/6/24
 */
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException {

        response.setHeader(JwtConstants.CONTENT_TYPE_KEY, JwtConstants.CONTENT_TYPE_VALUE);
        response.setStatus(ResultCode.SUCCESS.getCode());
        response.getWriter().write(JSONObject.toJSONString(ResultVO.successResult(new Object())));
    }
}
