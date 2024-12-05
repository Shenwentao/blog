package cn.xiaqileyu.blog.security;

import cn.xiaqileyu.blog.constant.ResultCode;
import cn.xiaqileyu.blog.util.CommonUtil;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义身份认证验证组件
 *
 * @author swt
 * @date 2020/6/24
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userName = authentication.getName();
        String password = CommonUtil.encrypt(authentication.getCredentials().toString());
        // 认证逻辑
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        if (null != userDetails) {
            if (password.equals(userDetails.getPassword())) {

                // 生成令牌 这里令牌里面存入了:name,password 当然你也可以放其他内容
                return new UsernamePasswordAuthenticationToken(userName, password);
            } else {
                throw new BadCredentialsException(ResultCode.PASSWORD_IS_INCORRECT.getMsg());
            }
        } else {
            throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMsg());
        }
    }

    /**
     * 是否可以提供输入类型的认证服务
     * @param authentication 认证组件
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
