package com.academo.controller;

import com.academo.controller.dtos.file.FileDTO;
import com.academo.model.File;
import com.academo.model.User;
import com.academo.security.authuser.AuthUser;
import com.academo.service.file.IFileService;
import com.academo.util.FileTransfer.service.DriveService;
import org.apache.coyote.Response;
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
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private IFileService fileService;

    @Autowired
    private DriveService driveService;

    @PostMapping("/upload-file")
    public ResponseEntity<FileDTO> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("subjectId") Integer subjectId, Authentication authentication){
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();

        File uploadedFile = fileService.createFile(file, userId, subjectId);
        URI uri = URI.create(uploadedFile.getPath());
        FileDTO fileDto = new FileDTO(
                uploadedFile.getUuid(),
                uploadedFile.getFileName(),
                uploadedFile.getPath(),
                uploadedFile.getFileType(),
                uploadedFile.getSize(),
                uploadedFile.getSubject().getId(),
                uploadedFile.getCreatedAt()
        );

        return ResponseEntity.created(uri).body(fileDto);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId) {
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

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<String> deleteFile(@PathVariable String uuid, Authentication authentication) {
            Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
            fileService.deleteFile(uuid, userId);
            return ResponseEntity.ok("Arquivo deletado com sucesso!");
    }


    @GetMapping
    public ResponseEntity<List<FileDTO>> getFiles(@RequestParam Integer subjectId){
        List<FileDTO> files = fileService.findAllFilesBySubjectId(subjectId).stream()
                .map( file -> new FileDTO(
                        file.getUuid(),
                        file.getFileName(),
                        file.getPath(),
                        file.getFileType(),
                        file.getSize(),
                        file.getSubject().getId(),
                        file.getCreatedAt())).toList();
        return ResponseEntity.ok(files);
    }





}
