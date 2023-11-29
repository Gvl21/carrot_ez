package com.morecommit.carrotEz.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserReository {
    public boolean existsByEmail(String email) {
        return false;
    }
}
