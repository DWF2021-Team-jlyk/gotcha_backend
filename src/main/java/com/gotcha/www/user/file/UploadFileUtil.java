package com.gotcha.www.user.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UploadFileUtil {
	
	
	private static final Logger log = LoggerFactory.getLogger(UploadFileUtil.class);

	
	// 파일 업로드
	public static void fileUpload(String uploadPath, String fileName, byte[] fileData) throws IOException {
		
		int data;
		log.info("[uploadPath] " + uploadPath);
		log.info("[fileName] " + fileName);
		File saveFile = new File(uploadPath, fileName);
		FileOutputStream out = new FileOutputStream(saveFile);
		
		while((data = System.in.read()) != -1) {
			out.write(fileData);
		}
		System.out.println("finish");
		out.close();
		System.out.println("finish");
	}
	
	// 파일 생성
	public static void mkdirDir(String uploadPath) {
		
		File uploadFolder = new File(uploadPath);
		if(!uploadFolder.exists()) {
			uploadFolder.mkdirs();
		}
		System.out.println("finish");
	}
}
