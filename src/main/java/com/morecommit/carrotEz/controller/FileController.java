package com.morecommit.carrotEz.controller;

import com.morecommit.carrotEz.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
//http://ec2-13-209-42-166.ap-northeast-2.compute.amazonaws.com/file/e364731d-a698-4dc8-8bbc-7d054dee06d1.png
    private final FileService fileService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String url = fileService.upload(file);
        return url;
    }

//    @GetMapping(value = "{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
//    public Resource getImage(
//            @PathVariable("fileName") String fileName) {
//        Resource resource = fileService.getImage(fileName);
//        return resource;
//    }

    @GetMapping(value = "/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        Resource resource = fileService.getImage(fileName);

        if (resource == null) {
            return ResponseEntity.notFound().build();
        }

        // Content-Disposition 헤더를 설정하여 브라우저가 이미지를 다운로드하지 않고 표시하도록 함
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
