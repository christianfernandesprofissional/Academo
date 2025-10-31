package com.academo.service.file;

import com.academo.model.File;
import com.academo.model.Subject;
import com.academo.model.User;
import com.academo.repository.FileRepository;
import com.academo.repository.UserRepository;
import com.academo.service.subject.SubjectServiceImpl;
import com.academo.service.user.IUserService;
import com.academo.util.FileTransfer.service.DriveService;
import com.academo.util.exceptions.FileTransfer.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Transient;
import java.util.List;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private SubjectServiceImpl subjectService;

    @Autowired
    private DriveService driveService;

    @Transactional
    @Override
    public File createFile(MultipartFile file, Integer userId, Integer subjectId) {

        isMimeTypeValid(file);
        isFileSizeValid(file);

        User user = userService.findById(userId);
        isUserStorageFull(file, user);
        Subject subject = subjectService.findById(subjectId);

        String driveFileId = null;
        try {
            driveFileId = driveService.uploadFile(file);
        } catch (Exception e) {
            throw new FileStorageException();
        }

        String completePath = "http://localhost:8080/download/" + driveFileId;
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
        return fileRepository.findAllBySubjectId(subjectId).orElseThrow(FileNotFoundException::new);
    }

    @Override
    public void deleteFile(String uuid, String path) {
        File file = fileRepository.findById(uuid).orElseThrow(FileNotFoundException::new);
        try {
            driveService.deleteFile(uuid);
        }catch (Exception e) {
            //
        }

    }


    // -------> MÉTODOS AUXILIARES <--------


    private Boolean isMimeTypeValid(MultipartFile file) {
        if(!file.getContentType().equalsIgnoreCase("image/jpeg") ||
                !file.getContentType().equalsIgnoreCase("image/png")  ||
                !file.getContentType().equalsIgnoreCase("application/pdf") ||
                !file.getContentType().equalsIgnoreCase("application/msword") ||
                !file.getContentType().equalsIgnoreCase("application/vnd.openxmlformats-officedocument.wordprocessingml.document") ||
                !file.getContentType().equalsIgnoreCase("text/csv") || !file.getContentType().equalsIgnoreCase("text/plain")) {
            throw new MimeTypeException();
        }
        return true;
    }

    private Boolean isFileSizeValid(MultipartFile file) {
        //RETORNAR EXCEÇÃO
        if(file.getSize() > 15000000L) throw new FileSizeException();
        return true;
    }

    private Boolean isUserStorageFull(MultipartFile file, User user) {
        if(user.getStorageUsage() + file.getSize() > 300000000L) {
            throw new UserStorageIsFullException();
        }
        return true;
    }
}
