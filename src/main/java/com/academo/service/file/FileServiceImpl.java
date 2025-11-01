package com.academo.service.file;

import com.academo.model.File;
import com.academo.model.Subject;
import com.academo.model.User;
import com.academo.repository.FileRepository;
import com.academo.service.subject.SubjectServiceImpl;
import com.academo.service.user.UserServiceImpl;
import com.academo.util.FileTransfer.service.DriveService;
import com.academo.util.exceptions.FileTransfer.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private SubjectServiceImpl subjectService;

    @Autowired
    private DriveService driveService;

    private static final long ONE_MB = 1024L * 1024L;

    private static final long FIFTEEN_MB = 15 * ONE_MB;

    private static final long THREE_HUNDRED_MB = 300 * ONE_MB;

    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/jpeg",
            "image/png",
            "application/pdf",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "text/csv",
            "text/plain"
    );

    @Transactional
    @Override
    public File createFile(MultipartFile file, Integer userId, Integer subjectId) {

        Subject subject = subjectService.findById(subjectId);
        User user = userService.findById(userId);
        isUserStorageFull(file, user);
        isMimeTypeValid(file);
        isFileSizeValid(file);


        String driveFileId = null;
        try {
            driveFileId = driveService.uploadFile(file);
        } catch (Exception e) {
            throw new FileStorageException("Erro ao fazer o upload do arquivo!");
        }

        Long newStorage = user.getStorageUsage() + file.getSize();
        user.setStorageUsage(newStorage);
        userService.update(user);

        String completePath = "http://localhost:8080/files/download/" + driveFileId;
        File f = new File(file.getOriginalFilename(), completePath, file.getContentType(), file.getSize());
        f.setUser(user);
        f.setSubject(subject);

        return fileRepository.save(f);
    }

    @Override
    public File findFileById(String uuid){
        return fileRepository.findById(uuid).orElseThrow(FileNotFoundException::new);
    }

    @Override
    public List<File> findAllFilesBySubjectId(Integer subjectId) {
        subjectService.findById(subjectId);
        return fileRepository.findAllBySubjectId(subjectId).orElseThrow(FileNotFoundException::new);
    }

    @Transactional
    @Override
    public void deleteFile(String uuid, Integer userId) {
        User user = userService.findById(userId);
        File file = fileRepository.findById(uuid).orElseThrow(FileNotFoundException::new);

        long newUserStorage = user.getStorageUsage() - file.getSize();
        user.setStorageUsage(newUserStorage);

        String drivePath = file.getPath().substring(37);

        fileRepository.deleteById(uuid);
        userService.update(user);
        try {
            driveService.deleteFile(drivePath);
        } catch (Exception e) {
           throw new FileStorageException("Erro ao deletar arquivo!");
        }
    }


    // -------> MÉTODOS AUXILIARES <--------


    private Boolean isMimeTypeValid(MultipartFile file) {
        String contentType = file.getContentType();

        if (contentType == null || !ALLOWED_TYPES.contains(contentType.toLowerCase())) {
            throw new MimeTypeException();
        }
        return true;
    }

    private Boolean isFileSizeValid(MultipartFile file) {
        //RETORNAR EXCEÇÃO
        if(file.getSize() > FIFTEEN_MB) throw new FileSizeException();
        return true;
    }

    private Boolean isUserStorageFull(MultipartFile file, User user) {
        if(user.getStorageUsage() + file.getSize() > THREE_HUNDRED_MB) {
            throw new UserStorageIsFullException();
        }
        return true;
    }
}
