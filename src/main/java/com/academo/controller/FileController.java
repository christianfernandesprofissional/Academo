package com.academo.controller;

import com.academo.model.File;
import com.academo.model.User;
import com.academo.security.authuser.AuthUser;
import com.academo.service.file.IFileService;
import com.academo.util.FileTransfer.service.DriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private IFileService fileService;


    @PostMapping("/upload-file")
    public ResponseEntity<File> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("subjectId") Integer subjectId, Authentication authentication){
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();

        File uploadedFile = fileService.createFile(file, userId, subjectId);
        URI uri = URI.create(uploadedFile.getPath());

        return ResponseEntity.created(uri).body(uploadedFile);
    }

//    @GetMapping("/download/{fileId}")
//    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId, Authentication authentication) {
//        DriveService.DownloadedFile downloaded = null;
//        try {
//            downloaded = driveService.getFile(fileId);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        ByteArrayResource resource = new ByteArrayResource(downloaded.content());
//
//        String mimeType = downloaded.mimeType() != null ? downloaded.mimeType() : MediaType.APPLICATION_OCTET_STREAM_VALUE;
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + downloaded.name() + "\"")
//                .contentType(MediaType.parseMediaType(mimeType))
//                .body(resource);
//    }
//
//    @DeleteMapping("/delete/{fileId}")
//    public ResponseEntity<String> deleteFile(@PathVariable String fileId, Authentication authentication) {
//        try {
//            driveService.deleteFile(fileId);
//            return ResponseEntity.ok("Arquivo deletado com sucesso!");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Erro ao deletar o arquivo: " + e.getMessage());
//        }
//    }





}
