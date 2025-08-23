package com.academo.repository;

import com.academo.model.Scores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ScoresRepository extends JpaRepository <Scores, Integer> {
}
