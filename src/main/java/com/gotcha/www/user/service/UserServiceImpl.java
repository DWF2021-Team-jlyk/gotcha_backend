package com.gotcha.www.user.service;

import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gotcha.www.user.dao.UserDAO;
import com.gotcha.www.user.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// id 중복 검사
	@Override
	public boolean checkId(String user_id) {
		int countId = userDAO.checkId(user_id);
		if(countId > 0) {
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public void insertUser(UserVO userVO) {
		String rawPassword = userVO.getUser_pwd();
		// 비밀번호 암호화
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		userVO.setUser_pwd(encPassword);
		log.info("[JOIN ENCODE PASSWORD] " + userVO.getUser_pwd());
		userDAO.insertUser(userVO);
	}
	
	@Override
	public String sendToEmail(String division,String toMail) {
		Random random=new Random();  //난수 생성을 위한 랜덤 클래스
		String code="";  //인증번호 
		String chars = "abcdefghijklmnopqrstuvwxyz";
		String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String NUMS = "1234567890";
		String SPEC = "!@#$%&";
		
		log.info("mail : " + toMail);
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setProtocol("smtp");
		mailSender.setUsername("jsu0269@gmail.com");
		mailSender.setPassword("fkstbwlmuwtphwle");

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.debug", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.EnableSSL.enable", "true");
		mailSender.setJavaMailProperties(prop);

		try {
			MimeMessage mimeMsg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMsg, true, "UTF-8");

			//messageHelper.setFrom(new InternetAddress("jsu917@gmail.com"));
			
			messageHelper.setTo(new InternetAddress(toMail));//수취인
			
			if(division.equals("join")) {
				// code 생성
				for(int i =0; i<6;i++) {
					int index = random.nextInt(9)+1; // 1 ~ 9 code 생성
					code += index;
				}
				log.info("[code] " + code);
				messageHelper.setSubject("안녕하세요. [GotCha] 인증 코드입니다.");//메일 title
				messageHelper.setText("인증코드 : " + code, true);
			} else if(division.equals("find")) {
				
				code = createRandomPassword(SPEC) + createRandomPassword(CHARS) + createRandomPassword(chars) + createRandomPassword(NUMS);
				log.info("[createPassword] " + code);
				
				messageHelper.setSubject("안녕하세요. [GotCha] 임시 비밀번호 입니다.");//메일 title
				messageHelper.setText("임시 비밀번호 : " + code, true);
				
				// 비밀번호 암호화
				String encPassword = bCryptPasswordEncoder.encode(code);
				userDAO.updatePwd(toMail, encPassword);
			}
			mailSender.send(mimeMsg);
			
		} catch (AddressException e) {
			e.printStackTrace();
			System.out.println("AddressException : " + e.getMessage());
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println("MailException : " + e.getMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("MessagingException : " + e.getMessage());
		}
		
		return code;
	}

	// 비밀번호 찾기(비밀번호 생성)
	private String createRandomPassword(String src) {
		Random rnd = new Random();
	    int index1 = (int) (rnd.nextFloat() * src.length())
	    		, index2 = (int) (rnd.nextFloat() * src.length());
	    		
	    return "" + src.charAt(index1) + src.charAt(index2);
	}
	
	@Override
	public boolean checkCode(String inputCode, String joinCode) {
		if(joinCode.equals(inputCode)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void updateEnabled(String user_id) {
		userDAO.updateEnabled(user_id);
	}

	
	
}
