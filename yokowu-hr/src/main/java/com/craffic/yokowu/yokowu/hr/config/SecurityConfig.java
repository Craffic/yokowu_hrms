package com.craffic.yokowu.yokowu.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 提供一个PasswordEncoder bean，用来加密密码
    @Bean
    PasswordEncoder passwordEncoder(){
        // 暂时使用明文存储，方法已过期必须使用加密密码
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/images/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated().
                and().
                formLogin().
                loginPage("/login.html").
                loginProcessingUrl("/doLogin").
                usernameParameter("userName").
                passwordParameter("password").
//--------------登录成功的分两种情况-----------------------------------------------
                // 服务端跳转：不管从哪个页面来的，登录成功后地址都是/success
//                successForwardUrl("/success").
                // 重定向: 会记录登录前的地址，比如登录前的地址是/hello，登录后就跳转到hello页面上
                // defaultSuccessUrl("/success", true) =  successForwardUrl("/success")
                defaultSuccessUrl("/success").
//--------------登录失败的都跳转到一个失败页面-----------------------------------------------
                // failureForwardUrl: 服务端跳转；failureUrl：重定向
                failureForwardUrl("/failure").
//                failureUrl("/failure").
//--------------注销logout默认是get请求，也可以post请求----------------------------------------------
                and().
                logout().
                logoutSuccessUrl("/login.html").
                // 清除session和authentication认证信息，不用配，默认就会清除
                invalidateHttpSession(true).
                clearAuthentication(true).
                permitAll().
                and().
                csrf().disable();
    }

    // 配置用户名密码（配置在内存里）
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 用户信息配置在内存里
        auth.inMemoryAuthentication().withUser("Craffic").password("123456").roles("admin");
    }

}
