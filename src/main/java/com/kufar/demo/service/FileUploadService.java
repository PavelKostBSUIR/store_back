package com.kufar.demo.service;

import com.kufar.demo.entity.User;
import com.kufar.demo.repo.ProductRepository;
import com.kufar.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

@Service
public class FileUploadService {
    private final UserService userService;

    @Autowired
    FileUploadService(UserService userService) {
        this.userService = userService;
    }

    public void saveFile(Long userId, Long productId, MultipartFile multipartFile, Principal principal) {
        User user = userService.findById(userId, principal);
        Long storedUserId = user.getId();
        Path uploadPath = Paths.get("res/users/" + storedUserId + "/products/" + productId + "/photos");

        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(multipartFile.getOriginalFilename());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void saveFiles(Long userId, Long productId, MultipartFile[] multipartFiles, Principal principal) {
        for (MultipartFile multipartFile : multipartFiles) {
            saveFile(userId, productId, multipartFile, principal);
        }
    }
}
