package com.gotcha.www;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gotcha.www.card.service.CardFileService;


@SpringBootApplication
public class GotchaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(GotchaApplication.class, args);
	}
	
//	@Bean
//	CommandLineRunner init(HomeService homeService) {
//		return (args) -> {
//			//서버 시작시 전체 업로드 경로의 파일 제거
////			homeService.deleteAll();
//			//파일 업로드 없을 경우 폴더 생성
//			homeService.init();
//		};
//	}

	@Bean
	CommandLineRunner init(CardFileService cardFileService) {
		return (args) -> {
			//서버 시작시 전체 업로드 경로의 파일 제거
			//storageService.deleteAll();
			//파일 업로드 없을 경우 폴더 생성
			cardFileService.init();
		};
	}
}
