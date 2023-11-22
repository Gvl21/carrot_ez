package com.morecommit.carrotEz.entity;

import com.morecommit.carrotEz.dto.request.board.PostBoardRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Entity(name = "board")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardNumber;
    private String title;
    private String content;
    private int commentCount;
    private String writeDatetime;
    private int favoriteCount;
    private int viewContent;
    private String writerEmail;

    public Board(PostBoardRequestDto dto, String email){

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);

        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.writeDatetime = writeDatetime;
        this.favoriteCount = 0;
        this.commentCount = 0;
        this.viewContent = 0;
        this.writerEmail = email;

    }

}
