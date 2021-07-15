package com.gotcha.www.user.file;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gotcha.www.home.vo.CardFileVO;

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
		
		if(newFileName != null) {
			FileOutputStream fos = new FileOutputStream(saveFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			bos.write(fileData);
			bos.flush();
			bos.close();
			log.info("[FILE UPLOAD SUCCESS]");
		}	
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

	public static void fileDelete(String fileDeletePath, String fileName) {
		File folder = new File(fileDeletePath);
		File parentFolder = new File(folder.getAbsoluteFile().getParent());
		log.info("[parentFolder ] " + parentFolder);
		File deleteFile = new File(fileDeletePath, fileName);

		if(deleteFile.exists()) {
			if(deleteFile.delete()){ 
				log.info("[file] 파일 삭제 성공");
				if(folder.exists())folder.delete();
				if(parentFolder != null && parentFolder.exists())parentFolder.delete();
				log.info("[folder] 폴더 삭제 성공");
			}
		}
		
		// 하위 파일 모두 삭제 및 폴더 삭제
		
//		File deleteFile = new File(fileDeletePath);
		
//		File folder = new File(fileDeletePath);
//		try {
//	        while (folder.exists()) { // 폴더가 존재한다면
//	            File[] listFiles = folder.listFiles();
//
//	            for (File file : listFiles) { // 폴더 내 파일을 반복시켜서 삭제
//	                file.delete();
//	            }
//
//	            if (listFiles.length == 0 && folder.isDirectory()) { // 하위 파일이 없는지와 폴더인지 확인 후 폴더 삭제
//	                folder.delete();
//	            }
//	        }
//	    } catch (Exception e){
//	        e.printStackTrace();
//	    }
	}

}
