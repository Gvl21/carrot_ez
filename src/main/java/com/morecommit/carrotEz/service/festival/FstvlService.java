package com.morecommit.carrotEz.service.festival;

import com.morecommit.carrotEz.entity.Fstvl;
import com.morecommit.carrotEz.repository.FstvlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class FstvlService {
    private final FstvlRepository fstvlRepository;
    public List<Fstvl> showFstvl(LocalDateTime currentTime) {
        LocalDateTime oneMonthAfter = currentTime.plusMonths(1);
        return fstvlRepository.findFstvlDetailList(currentTime, oneMonthAfter);
    }

}
