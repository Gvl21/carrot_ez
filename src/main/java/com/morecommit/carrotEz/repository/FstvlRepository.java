package com.morecommit.carrotEz.repository;

import com.morecommit.carrotEz.entity.Fstvl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface FstvlRepository extends JpaRepository<Fstvl, Long> {
    @Query("""
       select f from Fstvl f
        where :localDateTime in (f.startDate, f.endDate)
         or f.startDate <=
          (SELECT date_add(:localDateTime, interval 1 month));
    """)
    List<Fstvl> findFstvlDetailList(LocalDateTime localDateTime);
}
