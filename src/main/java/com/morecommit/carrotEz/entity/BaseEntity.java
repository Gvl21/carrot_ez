package com.morecommit.carrotEz.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass   // 공통 매핑 정보가 필요할 때 사용하는 애노테이션, 상속받는 자식 클래스에 매핑 정보 제공
public abstract class BaseEntity extends BaseTimeEntity {

    // 등록자
    @CreatedBy
    @Column(updatable = false, insertable = true)
    private String createdBy;

    // 수정자
    @LastModifiedBy
    private String modifiedBy;

}
