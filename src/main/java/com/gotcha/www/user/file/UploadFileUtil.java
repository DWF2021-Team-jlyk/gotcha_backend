package com.gotcha.www.user.file;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UploadFileUtil {
	
	
	private static final Logger log = LoggerFactory.getLogger(UploadFileUtil.class);

	
	// 파일 업로드
	public static void fileUpload(String uploadPath, String preFileName, String newFileName, byte[] fileData) throws IOException {
		
		int data;
		log.info("[uploadPath] " + uploadPath);
		log.info("[fileName] " + newFileName);
		log.info("[preFile] " + preFileName);
		
		
		File saveFile = new File(uploadPath, newFileName);
		
		if(preFileName != null) {
			File preFile = new File(uploadPath, preFileName);
			if(preFile.exists()) {
				if(preFile.delete()){ 
					log.info("[fileUpload] 파일 삭제 성공");
				}
			}
		}
		
		FileOutputStream fos = new FileOutputStream(saveFile);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		bos.write(fileData);
		bos.flush();
		bos.close();
		log.info("[FILE UPLOAD SUCCESS]");
	}
	
	// 파일 생성
	public static void mkdirDir(String uploadPath) {
		
		File uploadFolder = new File(uploadPath);
		log.info("[AbsolutePath] "+uploadFolder.getAbsolutePath());
		System.out.println("[foldername]"+uploadFolder);
		if(!uploadFolder.exists()) {
			uploadFolder.mkdirs();
			log.info("[FILE MKDIR SUCCESS]");
		}
	}
}
