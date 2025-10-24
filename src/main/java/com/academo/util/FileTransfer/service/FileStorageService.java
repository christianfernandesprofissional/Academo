package com.academo.util.FileTransfer.service;

import com.academo.model.File;
import com.academo.repository.FileRepository;
import com.academo.util.FileTransfer.FileStorageConfig;
import com.academo.util.exceptions.FileTransfer.FileStorageException;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Async
public class FileStorageService {

    @Autowired
    private FileRepository repository;

    @Autowired
    private Drive drive;

    @Autowired
    private GoogleDriveConfigService driveConfigService;

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception e){
            throw new FileStorageException("Não foi possível criar o diretório em que os arquivos foram armazenados");
        }
    }

    public String storeFile(MultipartFile file){
         String fileName = StringUtils.cleanPath(file.getOriginalFilename());
         try{
             if(fileName.contains("..")){
                 throw new FileStorageException("O nome do arquivo contém um caminho inválido " + fileName);
             }

             Path targetLocation = this.fileStorageLocation.resolve(fileName);
             Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

             return fileName;
         } catch (Exception e) {
             throw new FileStorageException("Não foi possível salvar o arquivo " + fileName + " Tente novamente!");
         }
    }


    public File storageOnDrive(Integer userId, MultipartFile receivedFile) {
        File file = new File();
        file.setFileName(receivedFile.getOriginalFilename());
        file.setFileType(receivedFile.getContentType());
        file.setSize(receivedFile.getSize());

        File createdFile = repository.save(file);



        String name = createdFile.getUuid() + "-" + file.getFileName();

        String userFolderName = "user" + userId;

        String userFolderId = findOrCreateFolder("files", userFolderId);




    }



}
