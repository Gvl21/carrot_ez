package com.morecommit.carrotEz.repository;

import com.morecommit.carrotEz.entity.Fstvl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public interface FstvlRepository extends JpaRepository<Fstvl, Long> {
@Query("""
       select f from Fstvl f
        where (:localDate between f.startDate and f.endDate)
          or (f.startDate between :localDate and :oneMonthAfter)
           order by f.startDate asc
""")
List<Fstvl> findFstvlDetailList(@Param("localDate") LocalDate localDate,
                                @Param("oneMonthAfter") LocalDate oneMonthAfter);
}
