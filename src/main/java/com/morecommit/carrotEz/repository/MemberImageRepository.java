package com.morecommit.carrotEz.repository;

import com.morecommit.carrotEz.entity.Member;
import com.morecommit.carrotEz.entity.MemberImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface MemberImageRepository extends JpaRepository<MemberImage, Long> {
    Image findByMember(Member member);
}
