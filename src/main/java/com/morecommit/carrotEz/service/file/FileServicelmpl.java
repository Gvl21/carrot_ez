package com.morecommit.carrotEz.service.file;

<<<<<<< HEAD
=======
import com.morecommit.carrotEz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
>>>>>>> 7c29f2478d889c888766069b5f52fbd3d84f6d87
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
=======
import org.springframework.transaction.annotation.Transactional;
>>>>>>> 7c29f2478d889c888766069b5f52fbd3d84f6d87
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
<<<<<<< HEAD
public class FileServicelmpl implements FileService {

=======
@RequiredArgsConstructor
@Transactional
public class FileServicelmpl implements FileService {

    private final MemberRepository memberRepository;

>>>>>>> 7c29f2478d889c888766069b5f52fbd3d84f6d87
    @Value("${file.path}")
    private String filePath;

    @Value("${file.url}")
    private String fileUrl;

    // 업로드
    @Override
    public String upload(MultipartFile file) {
        if(file.isEmpty()) return null; // 파일이 없을 때
        String originFileName = file.getOriginalFilename();
        String extension = originFileName.substring(originFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extension;
        String savePath = filePath + saveFileName;
<<<<<<< HEAD

=======
>>>>>>> 7c29f2478d889c888766069b5f52fbd3d84f6d87
        try{
            file.transferTo(new File(savePath));
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }

        String url = fileUrl + saveFileName;
<<<<<<< HEAD
=======

>>>>>>> 7c29f2478d889c888766069b5f52fbd3d84f6d87
        return url;
    }

    @Override
    public Resource getImage(String filName) {
        Resource resource = null;
        try{
<<<<<<< HEAD
            resource = new UrlResource("file : " + filePath + filName);
=======
            resource = new UrlResource("file:" + filePath + filName);
>>>>>>> 7c29f2478d889c888766069b5f52fbd3d84f6d87
        } catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
        return resource;
    }
<<<<<<< HEAD
}
=======

    }
>>>>>>> 7c29f2478d889c888766069b5f52fbd3d84f6d87
