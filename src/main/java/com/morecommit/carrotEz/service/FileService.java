package com.morecommit.carrotEz.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

// 파일 업로드
public interface FileService {
    String upload(MultipartFile file);
    Resource getImage(String filName);

}
