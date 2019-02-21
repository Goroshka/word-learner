package com.koldakov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koldakov.entity.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

}
