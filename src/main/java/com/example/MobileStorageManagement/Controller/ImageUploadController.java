package com.example.MobileStorageManagement.Controller;

import com.example.MobileStorageManagement.Adapter.CloudinaryAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageUploadController {

    private final CloudinaryAdapter cloudinaryAdapter;

    public ImageUploadController(CloudinaryAdapter cloudinaryAdapter) {
        this.cloudinaryAdapter = cloudinaryAdapter;
    }

    @PostMapping("/img-upload")
    public ResponseEntity<String> uploadImg(@RequestParam("file") MultipartFile file) {
        String url = cloudinaryAdapter.uploadImage(file, "img-folder");
        return ResponseEntity.ok(url);
    }

}
