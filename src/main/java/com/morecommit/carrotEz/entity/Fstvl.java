package com.morecommit.carrotEz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity @Getter @Setter
public class Fstvl {
    @Id
    @Column(name="fstvl_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fstvl_name")
    private String name;
    @Column(name = "fstvl_loc")
    private String location;
    @Column(name = "fstvl_date_start")
    private LocalDate startDate;
    @Column(name = "fstvl_date_end")
    private LocalDate endDate;
    @Column(name = "fstvl_detail")
    private String Detail;
    @Column(name = "fstvl_loc_detail")
    private String locationDtl;

}
