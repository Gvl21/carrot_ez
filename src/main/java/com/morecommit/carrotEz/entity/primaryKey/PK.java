package com.morecommit.carrotEz.entity.primaryKey;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PK implements Serializable {
    @Column(name="article_id")
    private Long articleId;
}
