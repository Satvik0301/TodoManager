package com.satvik.todomanager.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/file")
public class FileController {

    Logger logger = LoggerFactory.getLogger(FileController.class);

    //uplode single file
    @PostMapping("/single")
    public String uploadSigleFileHandler(@RequestParam("image") MultipartFile file) {
        logger.info("Uploading file");
        logger.info("File name: " + file.getName());
        logger.info("Content type: " + file.getContentType());
        logger.info("Original File name: " + file.getOriginalFilename());
        logger.info("File size: {}", file.getSize());

        return "File Test";
    }

    @PostMapping("/multiple")
    public String uploadMultipleFileHandler(@RequestParam("files") MultipartFile[] files) {

        Arrays.stream(files).forEach(file -> {
            logger.info("File name: " + file.getOriginalFilename());
            logger.info("Content type: " + file.getContentType());
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            //call service to upload files and pass file object
        });

        return "Handling Multiple File";
    }

    //serving image files in response
    @GetMapping("/serve-image")
    public void serveImageHandler(HttpServletResponse response) {
        //image
        try {

            InputStream fileInputStream = new FileInputStream("images/images.jpeg");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(fileInputStream, response.getOutputStream());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
