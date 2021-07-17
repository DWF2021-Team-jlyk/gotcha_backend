package com.gotcha.www.home.controller;

import com.gotcha.www.extra.service.FileService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RequestMapping("/image")
@RestController
public class ImageController {

    private final FileService fileService;
    private Log log = LogFactory.getLog(this.getClass());
    @Value("${service.file.uploadurl}")
    private String path;

    @Autowired
    public ImageController(FileService fileService){
        this.fileService = fileService;
    }

    @GetMapping(value = "/bg/{ws_id}/{ws_isImage}", produces = MediaType.ALL_VALUE)
    public ResponseEntity<byte[]> wsBgImage(@PathVariable("ws_id") int ws_id, @PathVariable("ws_isImage") String ws_isImage) throws IOException{
        InputStream imageStream = new FileInputStream(path+ws_id + "/bg/"+ws_isImage);
        log.info(path+ws_id + "/bg"+"/"+ws_isImage);
        byte[] imageByteArray = new byte[2048*1024*8];
        imageStream.read(imageByteArray);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }
}
