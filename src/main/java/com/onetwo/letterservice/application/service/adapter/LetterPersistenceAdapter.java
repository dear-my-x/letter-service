package com.onetwo.letterservice.application.service.adapter;

import com.onetwo.letterservice.adapter.out.persistence.entity.LetterEntity;
import com.onetwo.letterservice.adapter.out.persistence.repository.letter.LetterRepository;
import com.onetwo.letterservice.application.port.out.RegisterLetterPort;
import com.onetwo.letterservice.domain.Letter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class LetterPersistenceAdapter implements RegisterLetterPort {

    private final LetterRepository letterRepository;

    @Override
    @Transactional
    public Letter registerLetter(Letter newLetter) {
        LetterEntity letterEntity = LetterEntity.domainToEntity(newLetter);

        LetterEntity savedLetterEntity = letterRepository.save(letterEntity);

        return Letter.entityToDomain(savedLetterEntity);
    }
}
