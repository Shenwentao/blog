package cn.xiaqileyu.blog.config;

import cn.xiaqileyu.blog.security.CustomAuthenticationProvider;
import cn.xiaqileyu.blog.security.CustomLogoutSuccessHandler;
import cn.xiaqileyu.blog.security.JWTAuthenticationFilter;
import cn.xiaqileyu.blog.security.JWTLoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * SpringSecurity的配置
 * 通过SpringSecurity的配置，将JWTLoginFilter，JWTAuthenticationFilter组合在一起
 *
 * @author swt
 * @date 2020/6/24
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                //关闭csrf
                .csrf()
                .disable()
                //开放访问的请求
//                .authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                //swagger相关路径
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
                        "/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/swagge‌​r-ui.html").permitAll()
                //需要身份认证
//                .antMatchers("/**").authenticated().anyRequest().permitAll()
                .and()
                // 登陆过滤器
                .addFilter(new JWTLoginFilter(authenticationManager()))
                // 请求过滤器
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));

        http.logout().logoutSuccessHandler(new CustomLogoutSuccessHandler());
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(new CustomAuthenticationProvider(userDetailsService));

    }

}
