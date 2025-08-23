package com.academo.repository;

import com.academo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeActivityRepository extends JpaRepository<User,Integer> {
}
