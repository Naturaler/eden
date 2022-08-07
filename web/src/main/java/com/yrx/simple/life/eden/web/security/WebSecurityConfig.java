package com.yrx.simple.life.eden.web.security;

import com.yrx.simple.life.eden.application.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
                .authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .and()
                // by default uses a Bean by the name of corsConfigurationSource
                .cors(Customizer.withDefaults())
                .authorizeRequests()
                .antMatchers("/", "/login", "/*.html","/*.js","/*.css").permitAll()
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

        // 跨域时，需开启以下配置
        http.csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(new LoginEntryPoint());

        http.addFilterBefore(processingFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

    }

    // 跨域时，需开启以下配置
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
//        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

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
