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

        String url = fileUrl + saveFileName;

        return url;
    }

    @Override
    public Resource getImage(String filName) {
        Resource resource = null;
        try {
            resource = new UrlResource("file:" + filePath + filName);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return resource;
    }
}
