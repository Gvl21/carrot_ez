package com.morecommit.carrotEz.entity.image;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity(name = "Image")
@Table(name = "Image")

public class Image {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence;
    private int boardNumber;
    private String image;

    public Image(int boardNumber, String image){
        this.boardNumber = boardNumber;
        this.image = image;
    }

}
