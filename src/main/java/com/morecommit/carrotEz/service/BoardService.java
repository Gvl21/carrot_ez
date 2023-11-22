package com.morecommit.carrotEz.service;

import com.morecommit.carrotEz.dto.request.board.PostBoardRequestDto;
import com.morecommit.carrotEz.dto.response.board.PostBoardResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email){

    }
}
