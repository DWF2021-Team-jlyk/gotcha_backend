package com.gotcha.www.user.config;

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
import com.gotcha.www.user.filter.JwtAuthenticationFilter;
import com.gotcha.www.user.filter.JwtAuthorizationFilter;

import lombok.RequiredArgsConstructor;

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
//			.formLogin().loginPage("/login").and()
			.httpBasic().disable() //
			.authorizeRequests()
//			.antMatchers("admin").hasRole("ADMIN")
//			.antMatchers("member").hasAnyRole("ADMIN, MEMBER")
			.anyRequest().permitAll()
			.and()
			.addFilter(new JwtAuthenticationFilter(authenticationManager(), userDAO));
//			.addFilter(new JwtAuthorizationFilter(authenticationManager(),userDAO));
//			.exceptionHandling()
//			.authenticationEntryPoint(authenticationEntryPoint) // 시큐리티 필터에서 발생하는 예외를 try-catch로 잡는다.
//			.accessDeniedHandler(accessDeniedHandler) // 권한에서 예외가 발생;

	}	
	
}
