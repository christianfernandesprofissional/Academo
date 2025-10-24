package com.academo.util.FileTransfer.service;

import com.google.api.client.http.FileContent;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DriveService {

    @Autowired
    private Drive drive;

    public String createFolder(String name, String parentId) throws IOException {
        File metadata = new File();
        metadata.setName(name);
        metadata.setMimeType("application/vnd.google-apps.folder");
        if (parentId != null) metadata.setParents(List.of(parentId));

        File folder = drive.files().create(metadata).setFields("id").execute();
        return folder.getId();
    }

    public String uploadFile(MultipartFile multipartFile, String parentFolderId, String driveFileName) throws IOException {
        File metadata = new File();
        metadata.setName(driveFileName);
        metadata.setParents(List.of(parentFolderId));

        InputStreamContent content = new InputStreamContent(
                multipartFile.getContentType(),
                multipartFile.getInputStream()
        );

        File uploadedFile = drive.files()
                .create(metadata, content)
                .setFields("id, webViewLink, webContentLink")
                .execute();
        return uploadedFile.getWebViewLink();
    }
}
