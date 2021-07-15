package com.gotcha.www.extra.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final String BASIC_PATH = System.getProperty("user.dir") + "/src/main/resources/static/upload/";
    private final Log log = LogFactory.getLog(this.getClass());

    @Override
    public String addFile(final MultipartFile multipartFile, final int id, final String type)
            throws IOException {
        final String filePath = storeFilePath(id, type, multipartFile);
        final String folderPath = String.format("%s/%s/%d", BASIC_PATH, type, id);
        try{
            File folder = new File(folderPath);
            File file = new File(filePath);
            if(!folder.exists()){
                folder.mkdirs();
            }
            multipartFile.transferTo(file);
            return file.getName();
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @Override
    public File loadFile(final int id, final String type, final String fileName)
            throws IOException {
        final String filePath = getFilePath(id, type, fileName);
        File file = new File(filePath);
        return file;
    }

    @Override
    public boolean deleteFile(final int id, final String type, final String fileName)
            throws  IOException {
        return false;
    }

    private String storeFilePath(int id, String type, MultipartFile file){
        String originFileName = file.getOriginalFilename();
        String fileExtension = originFileName.substring(originFileName.lastIndexOf("."));
        String storedFileName = UUID.randomUUID().toString().replaceAll("-","") + fileExtension;
        return getFilePath(id, type, storedFileName);
    }

    private String getFilePath(int id, String type, String fileName) {
        return String.format("%s/%s/%d/%s", BASIC_PATH, type, id, fileName);
    }
}
