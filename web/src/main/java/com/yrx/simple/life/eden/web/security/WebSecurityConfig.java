package com.yrx.simple.life.eden.web.security;

import com.yrx.simple.life.eden.application.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private AuthenticationService authenticationService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(edenAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                .and()
//                // by default uses a Bean by the name of corsConfigurationSource
//                .cors(Customizer.withDefaults())
                .authorizeRequests()
                .antMatchers("/", "/login", "/*.html", "/*.js", "/*.css").permitAll()
//                .antMatchers("/antidote/list").hasRole("admin")
                .anyRequest().authenticated() // 联调时，需关闭鉴权校验
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/antidote/list")
//                .loginPage("/login")
//                .permitAll()
                .and()
                .logout()
                .permitAll()
//                .and().sessionManagement().enableSessionUrlRewriting(true)
        ;
        // 即使用了angular cli代理，也还是要禁用csrf，否则本地联调会报403异常
        http.csrf().disable();
//        http.exceptionHandling().authenticationEntryPoint(new LoginEntryPoint());

        http.addFilterBefore(processingFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    EdenAuthenticationProvider edenAuthenticationProvider() {
        return new EdenAuthenticationProvider(authenticationService);
    }

    EdenAuthenticationProcessingFilter processingFilter(AuthenticationManager authenticationManager) {
        EdenAuthenticationProcessingFilter filter = new EdenAuthenticationProcessingFilter();
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

}
