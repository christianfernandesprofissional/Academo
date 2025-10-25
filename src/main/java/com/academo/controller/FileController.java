package com.academo.controller;

import com.academo.model.File;
import com.academo.util.FileTransfer.service.DriveService;
import com.academo.util.FileTransfer.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/files")
public class FileController {

//    @Autowired
//    private FileStorageService fileStorageService;

    @Autowired
    private DriveService driveService;


    @PostMapping("/upload-file")
    private ResponseEntity<File> uploadFile(@RequestParam("file") MultipartFile file, Authentication authentication){

        String driveFileId = null;
        try {
            driveFileId = driveService.uploadFile(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Salvar metadados no banco
        File f = new File(file.getOriginalFilename(), driveFileId, file.getContentType(), file.getSize());
        //fileRepository.save(f);

        return ResponseEntity.ok(f);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId, Authentication authentication) {
        DriveService.DownloadedFile downloaded = null;
        try {
            downloaded = driveService.getFile(fileId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ByteArrayResource resource = new ByteArrayResource(downloaded.content());

        String mimeType = downloaded.mimeType() != null ? downloaded.mimeType() : MediaType.APPLICATION_OCTET_STREAM_VALUE;

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + downloaded.name() + "\"")
                .contentType(MediaType.parseMediaType(mimeType))
                .body(resource);
    }

    @DeleteMapping("/delete/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileId, Authentication authentication) {
        try {
            driveService.deleteFile(fileId);
            return ResponseEntity.ok("Arquivo deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar o arquivo: " + e.getMessage());
        }
    }

}
