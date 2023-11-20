package com.morecommit.carrotEz.repository;

import com.morecommit.carrotEz.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MemberRepository extends JpaRepository<Member,Long> {

    // 유무 확인
    boolean existsByEmail(String email);

    Member findByEmail(String email);

}
