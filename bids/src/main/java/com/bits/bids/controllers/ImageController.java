package com.bits.bids.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/images")
@CrossOrigin(originPatterns = "*")
public class ImageController {

    @Value("${img.storage.location}")
    private String imgStorageLocation;

    @GetMapping("/{imageName}")
    @ResponseBody
    public ResponseEntity<?> getImage(@PathVariable(name = "imageName", required = true) String imageName) {
            String imgExtension = imageName.split("\\.")[1];
            MediaType contentType = imgExtension.equals("jpg") || imgExtension.equals("jpeg") ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;
            Path imagePath = Paths.get(imgStorageLocation, imageName);
        InputStream in = null;
        try {
            in = new FileInputStream(imagePath.toAbsolutePath().toString());
        } catch (FileNotFoundException e) {
            Path defaultImagePath = Paths.get(imgStorageLocation, "default.jpeg");
            InputStream ins = null;
            try {
                ins = new FileInputStream(defaultImagePath.toAbsolutePath().toString());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(ins));
        }
        return ResponseEntity.ok().contentType(contentType).body(new InputStreamResource(in));
    }
}
