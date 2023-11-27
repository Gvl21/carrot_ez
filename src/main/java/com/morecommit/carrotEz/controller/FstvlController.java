package com.morecommit.carrotEz.controller;

import com.morecommit.carrotEz.entity.Fstvl;
import com.morecommit.carrotEz.service.FstvlService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FstvlController {

    private final FstvlService fstvlService;
    @GetMapping("/fstvl")
    public List<Fstvl> fstvlPost(){
        LocalDateTime currentTime = LocalDateTime.now();
        return fstvlService.showFstvl(currentTime);
    }
}
