package com.example.GrandWorldG.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring security config.
 * EnableGlobalMethodSecurity(prePostEnabled = true)?????????security??????????????????????@PreAuthorize?@PreFilter?????
 *
 * @author Hobbs.Heting.Zhao
 * @since 11/9/2021
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Any endpoint that requires defense against common vulnerabilities can be specified here.
     *
     * @param http
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.authorizeRequests()
                .anyRequest()
                .authenticated().and()
                .formLogin().and()
                .httpBasic();
    }

    /**
     * Endpoints specified in this method will be ignored by Spring Security, meaning it
     * will not protect them from CSRF, XSS, Clickjacking, and so on.
     *
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.inMemoryAuthentication().passwordEncoder(new PasswordEncoderImpl())
                .withUser("root")
                .password("root")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN", "USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
        //Permit some static resources.
        web.ignoring().antMatchers("/js/**");
    }
}
