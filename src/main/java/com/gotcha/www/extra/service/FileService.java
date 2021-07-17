package com.gotcha.www.extra.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileService {
    String addFile(final MultipartFile multipartFile, final int id, final String type) throws IOException;
    File loadFile(final int id, final String type, final String fileName) throws IOException;
    boolean deleteFile(final int id, final String type, final String fileName) throws IOException;
    void deleteAllFile(final int id, final String type) throws IOException;
}
