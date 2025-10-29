package com.academo.service.file;

import com.academo.model.File;
import com.academo.model.User;
import com.academo.repository.FileRepository;
import com.academo.repository.UserRepository;
import com.academo.service.user.IUserService;
import com.academo.util.FileTransfer.service.DriveService;
import com.academo.util.exceptions.FileTransfer.FileNotFoundException;
import com.academo.util.exceptions.FileTransfer.FileSizeException;
import com.academo.util.exceptions.FileTransfer.FileStorageException;
import com.academo.util.exceptions.FileTransfer.UserStorageIsFullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    IUserService userService;

    @Autowired
    private DriveService driveService;

    @Override
    public File createFile(MultipartFile file, Integer userId) {

        String driveFileId = null;
        try {
            driveFileId = driveService.uploadFile(file);
        } catch (Exception e) {
            throw new FileStorageException();
        }

        File f = new File(file.getOriginalFilename(), driveFileId, file.getContentType(), file.getSize());

        File createdFile = fileRepository.save(f);

        return createdFile;
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
            return false;
        }
        return true;
    }

    private Boolean isFileSizeValid(MultipartFile file) {
        //RETORNAR EXCEÇÃO
        if(file.getSize() > 15000000L) throw new FileSizeException();
        return true;
    }

    private Boolean isUserStorageFull(MultipartFile file, Integer userId) {
        User user = userService.findById(userId);
        if(user.getStorageUsage() + file.getSize() > 300000000L) {
            throw new UserStorageIsFullException();
        }
        return true;
    }
}
