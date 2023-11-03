package com.example.springfileuploadtutorial.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService {

    void store(MultipartFile file) {
        if (file.isEmpty()) {
            throw new StorageException("파일이 비어있습니다.");
        }

        Path destinationFile = Paths.get("uploaded").resolve(
                Paths.get(file.getOriginalFilename())
        ).normalize().toAbsolutePath();

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
