package com.morecommit.carrotEz.repository;

import com.morecommit.carrotEz.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {


}
