package com.darisamor.logosShop.service.impl;

import com.darisamor.logosShop.exception.NotFoundException;
import com.darisamor.logosShop.service.FileStorageService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageServiceImpl( @Value("${custom.path.file-directory}") String customPathFileDirectory){
        String uploadsDir = System.getProperty("user.dir") +
                System.getProperty("file.separator") +
                customPathFileDirectory;
        this.fileStorageLocation = Paths.get(uploadsDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e){
            log.error("Directory creation failed ({})", uploadsDir, e);
        }
    }

    @SneakyThrows
    @Override
    public boolean isExists(String fileName) {
        Path filePath = this.fileStorageLocation.resolve(fileName);
//        Path filePath = this.fileStorageLocation.resolve(uploadsDir + FILE_SEPARATOR + fileName);
        Resource resource = new UrlResource(filePath.toUri());
        return resource.exists();
    }

    @Override
    public String storeFile(MultipartFile file) {
        String filename;
        do{
            filename = UUID.randomUUID().toString() + "." + file.getOriginalFilename().split("\\.")[1];
        }while (isExists(filename));

        Path targetLocation = this.fileStorageLocation.resolve(filename);
        try {
            Files.copy(file.getInputStream(), targetLocation);
        } catch (IOException e){
            log.error("File copying error", e);
            filename = null;
        }
        return filename;
    }

    @Override
    public Resource loadFileByFileName(String fileName) {
//        Path filePath = this.fileStorageLocation.resolve(uploadsDir + FILE_SEPARATOR + fileName);
//        Path filePath = this.fileStorageLocation.resolve( fileName);
//        try{
//            Resource resource = new UrlResource(filePath.toUri());
//            if (resource.exists()) return resource;
//            else throw new Exception("File not found");
//        } catch (Exception e){
//            log.error("File downloading error", e);
//        }

        if(isExists(fileName)){
            try{
                return new UrlResource(fileStorageLocation.resolve(fileName).toUri());
            } catch (MalformedURLException e){
                log.error("File downloading error", e);
            }
        } else throw new NotFoundException("File", "filename", fileName);
        return null;
    }
}
