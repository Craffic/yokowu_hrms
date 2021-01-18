package com.craffic.yokowu.yokowu.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

    // 配置用户名密码（配置在内存里）
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 用户信息配置在内存里
        auth.inMemoryAuthentication().withUser("Craffic").password("123456").roles("admin");
    }

}
