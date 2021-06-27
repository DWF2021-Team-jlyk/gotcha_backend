package com.gotcha.www.user.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gotcha.www.user.dao.UserDAO;
import com.gotcha.www.user.vo.UserDto;

import lombok.RequiredArgsConstructor;

// 로그인 요청이 올 때 동작을 한다.
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
	
	private final UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("PrincipalDetailsService의 loadUserByUsername()");
		System.out.println("username: " + username);
		UserDto userDto = userDAO.findByUsername(username);
		System.out.println("userDtoRepository : " + userDto);
		return new PrincipalDetails(userDto);
	}
	
	
}
