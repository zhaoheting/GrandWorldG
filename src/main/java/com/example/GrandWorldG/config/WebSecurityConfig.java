package com.example.GrandWorldG.config;

import com.example.GrandWorldG.service.impl.GrandWorldUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Spring security config.
 * EnableGlobalMethodSecurity(prePostEnabled = true) when we add this annotation, we can use @PreAuthorize, @PreFilter and so on.
 *
 * @author Hobbs.Heting.Zhao
 * @since 11/9/2021
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * configure sth related to authentication, including which endpoints need to be authenticate, how to authenticate
     * ,where is the destination after login successfully or unsuccessfully.
     *
     * @param http
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()//Or Cross-site request forgery will be enabled.
                .authorizeRequests()
                .antMatchers("/auth/**","/js/**","/css/**","/**Insertion").permitAll()//Or the js and css can't be used.
//                .antMatchers("/index").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout?success")
                .permitAll();
    }

    /**
     * Do the authentication.
     *
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new GrandWorldUserDetailService();
    }
}
