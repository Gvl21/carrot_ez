package com.morecommit.carrotEz.service;

import com.morecommit.carrotEz.entity.Fstvl;
import com.morecommit.carrotEz.repository.FstvlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FstvlService {
    private final FstvlRepository fstvlRepository;
    public List<Fstvl> showFstvl(LocalDateTime currentTime) {
        List<Fstvl> fstvlDetailList = fstvlRepository.findFstvlDetailList(currentTime);

        return fstvlDetailList;
    }
}
