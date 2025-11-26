package com.example.MobileStorageManagement.Adapter;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryAdapter {
    String uploadImage(MultipartFile file, String folder);

    String uploadImage(byte[] imageData, String folder);

}
