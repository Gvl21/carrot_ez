package com.morecommit.carrotEz.service.board;

import com.morecommit.carrotEz.dto.request.board.PostBoardRequestDto;
import com.morecommit.carrotEz.dto.response.ResponseDto;
import com.morecommit.carrotEz.dto.response.board.PostBoardResponseDto;
import com.morecommit.carrotEz.entity.Board;
import com.morecommit.carrotEz.repository.BoardRepository;
import com.morecommit.carrotEz.repository.UserReository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final UserReository userReository;

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {
       try{
            boolean existedEmail = userReository.existsByEmail(email);
            if (!existedEmail)
                return PostBoardResponseDto.notExistUser();

            Board board = new Board(dto, email);
            boardRepository.save(board);

       }catch (Exception exception) {
           exception.printStackTrace();
           return ResponseDto.databaseError();
       }
       return PostBoardResponseDto.success();
    }

}
