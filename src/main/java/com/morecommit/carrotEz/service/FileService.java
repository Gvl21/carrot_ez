package com.morecommit.carrotEz.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

// 파일 업로드
public interface FileService {

    // 파일 받아와서 접근 가능한 url 반환
    String upload(MultipartFile file);

    // 접근한 url로 파일을 내보내주는 이미지 서비스
    Resource getImage(String filName);

}
