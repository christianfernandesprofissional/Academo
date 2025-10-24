package com.academo.controller;

import com.academo.model.File;
import com.academo.util.FileTransfer.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload-file")
    private ResponseEntity<File> uploadFile(@RequestParam MultipartFile file, Authentication authentication){

        System.out.println("File original name:" + file.getOriginalFilename());
        String filename = fileStorageService.storeFile(file);
        filename = file.getOriginalFilename();


        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download-file")
                .queryParam("fileName",filename)
                .toUriString();

        File fileInfo = new File(filename, fileDownloadUri, file.getContentType(), file.getSize());

        return ResponseEntity.ok(fileInfo);
    }

}
