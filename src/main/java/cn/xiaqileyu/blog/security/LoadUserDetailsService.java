package cn.xiaqileyu.blog.security;

import cn.xiaqileyu.blog.domain.vo.UserVO;
import cn.xiaqileyu.blog.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 身份验证
 *
 * @author swt
 * @date 2020/6/24
 */
@Component
public class LoadUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserService managementService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        UserVO userVO = managementService.queryUserByName(username).getData();

        // 判断用户是否存在
        if(userVO == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 添加权限
        authorities.add(new SimpleGrantedAuthority(userVO.getRole()));

        // 返回UserDetails实现类
        return new User(userVO.getUserName(), userVO.getPassword(), authorities);
    }
}
