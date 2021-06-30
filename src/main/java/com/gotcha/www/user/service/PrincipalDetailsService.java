package com.gotcha.www.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gotcha.www.user.dao.UserDAO;
import com.gotcha.www.user.vo.PrincipalDetails;
import com.gotcha.www.user.vo.UserDto;

import lombok.RequiredArgsConstructor;

// 로그인 요청이 올 때 동작을 한다.
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
	
	private static final Logger log = LoggerFactory.getLogger(PrincipalDetailsService.class);
	
	private final UserDAO userDAO;

	@Override
	@Transactional(rollbackFor=UsernameNotFoundException.class)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("PrincipalDetailsService의 loadUserByUsername()");
		log.info("username: " + username);
		UserDto userDto = userDAO.findByUsername(username);
		userDAO.updateLastLogin(username);
		
		if(userDto.getRole_type() == null || userDto.getRole_type().equals("")) {
			userDto.setRole_type("ROLE_USER");
		}

		log.info("principalDetails findByUsername : " + userDto);
		return new PrincipalDetails(userDto);
	}
	
}
