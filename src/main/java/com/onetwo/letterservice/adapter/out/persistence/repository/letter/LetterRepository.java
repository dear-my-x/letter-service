package com.onetwo.letterservice.adapter.out.persistence.repository.letter;

import com.onetwo.letterservice.adapter.out.persistence.entity.LetterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<LetterEntity, Long> {
}
