package com.gotcha.www.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

import com.gotcha.www.user.dao.UserDAO;
import com.gotcha.www.user.exception.CustomAccessDeniedHandler;
import com.gotcha.www.user.exception.CustomAuthenticationEntryPoint;
import com.gotcha.www.user.filter.JwtAuthenticationFilter;
import com.gotcha.www.user.filter.JwtAuthorizationFilter;

import lombok.RequiredArgsConstructor;

/**
 * 작성일 : 2021-06-21
 * 작성자 : 장승업
 * 시큐리티 설정
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final CorsFilter corsFilter;
	private final UserDAO userDAO;
	
	// 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
	
	@Autowired
	CustomAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	CustomAccessDeniedHandler accessDeniedHandler;
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/resources/**")
            .antMatchers("/user/**");
//            .antMatchers("/home/wsList")
//            .antMatchers("/home/notiList")
//            .antMatchers("/home/favUpdate")
//            .antMatchers("/home/wsUserList");
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			//.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class)
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session을 사용하지 않겠다.
			.and()
			.addFilter(corsFilter) // @CrossOrigin(인증 X), 시큐리티 필터에 등록 인증(O)
			.formLogin().disable()
//			.anonymous().disable()
//			.formLogin().loginPage("/login").and()
			.httpBasic().disable() //			
//			.exceptionHandling()
//			.authenticationEntryPoint(authenticationEntryPoint) // 시큐리티 필터에서 발생하는 예외를 try-catch로 잡는다.
//			.accessDeniedHandler(new CustomAccessDeniedHandler()) // 권한에서 예외가 발생;
//			.accessDeniedHandler(accessDeniedHandler)
//			.and()
			.authorizeRequests()
			.antMatchers("/main/wsList/list/**").access("hasAnyRole('ROLE_USER')")
			.antMatchers("/home/**").access("hasAnyRole('ROLE_USER')")
//			.antMatchers("/main/wsList/list/**").access("hasAnyRole('ROLE_ADMIN') or hasAnyRole('ROLE_MEMBER')")
//			.antMatchers("member").hasAnyRole("ADMIN, MEMBER")
//			.antMatchers("/user/*").permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilter(new JwtAuthenticationFilter(authenticationManager(), userDAO))
			.addFilter(new JwtAuthorizationFilter(authenticationManager(),userDAO));
//			.exceptionHandling()
//			.authenticationEntryPoint(authenticationEntryPoint) // 시큐리티 필터에서 발생하는 예외를 try-catch로 잡는다.
//			.accessDeniedHandler(accessDeniedHandler) // 권한에서 예외가 발생;

	}	
	
}
