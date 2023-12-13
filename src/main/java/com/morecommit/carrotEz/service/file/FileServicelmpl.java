package com.morecommit.carrotEz.service.file;

import com.morecommit.carrotEz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FileServicelmpl implements FileService {

    private final MemberRepository memberRepository;

    @Value("${file.path}")
    private String filePath;

    @Value("${file.url}")
    private String fileUrl;

    // 업로드
    @Override
    public String upload(MultipartFile file) {
        if (file.isEmpty()) return null; // 파일이 없을 때
        String originFileName = file.getOriginalFilename();
        String extension = originFileName.substring(originFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extension;
        String savePath = filePath + saveFileName;
        try {
            file.transferTo(new File(savePath));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        String url = "/file/" + saveFileName;

        return url;
    }

    @Override
    public Resource getImage(String fileName) {
        Resource resource = null;
        try {
            resource = new UrlResource("file://" + filePath + fileName);
            // file:/home/ec2-user/fileUpload/e364731d-a698-4dc8-8bbc-7d054dee06d1.png 호출

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return resource;
    }
}