package com.onetwo.letterservice.application.service.adapter;

import com.onetwo.letterservice.adapter.out.persistence.entity.LetterEntity;
import com.onetwo.letterservice.adapter.out.persistence.repository.letter.LetterRepository;
import com.onetwo.letterservice.application.port.out.ReadLetterPort;
import com.onetwo.letterservice.application.port.out.RegisterLetterPort;
import com.onetwo.letterservice.application.port.out.UpdateLetterPort;
import com.onetwo.letterservice.domain.Letter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LetterPersistenceAdapter implements RegisterLetterPort, ReadLetterPort, UpdateLetterPort {

    private final LetterRepository letterRepository;

    @Override
    @Transactional
    public Letter registerLetter(Letter newLetter) {
        LetterEntity letterEntity = LetterEntity.domainToEntity(newLetter);

        LetterEntity savedLetterEntity = letterRepository.save(letterEntity);

        return Letter.entityToDomain(savedLetterEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Letter> findById(Long letterId) {
        Optional<LetterEntity> optionalLetterEntity = letterRepository.findById(letterId);

        if (optionalLetterEntity.isPresent()) {
            Letter letter = Letter.entityToDomain(optionalLetterEntity.get());

            return Optional.of(letter);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public void updateLetter(Letter letter) {
        LetterEntity letterEntity = LetterEntity.domainToEntity(letter);

        letterRepository.save(letterEntity);
    }
}
