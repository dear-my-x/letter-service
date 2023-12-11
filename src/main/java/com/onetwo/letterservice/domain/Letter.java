package com.onetwo.letterservice.domain;

import com.onetwo.letterservice.adapter.out.persistence.entity.LetterEntity;
import com.onetwo.letterservice.application.port.in.command.RegisterLetterCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Letter extends BaseDomain {

    private Long id;

    private String userId;

    private String targetUserId;

    private String content;

    private Boolean arrived;

    private Boolean received;

    private Boolean state;

    public static Letter createNewLetterByCommand(RegisterLetterCommand registerLetterCommand) {
        Letter letter = new Letter(
                null,
                registerLetterCommand.getUserId(),
                registerLetterCommand.getTargetUserId(),
                registerLetterCommand.getContent(),
                false,
                false,
                false
        );

        letter.setDefaultState();
        return letter;
    }

    public static Letter entityToDomain(LetterEntity letterEntity) {
        Letter letter = new Letter(
                letterEntity.getId(),
                letterEntity.getUserId(),
                letterEntity.getTargetUserId(),
                letterEntity.getContent(),
                letterEntity.getArrived(),
                letterEntity.getReceived(),
                letterEntity.getState()
        );

        letter.setMetaDataByEntity(letterEntity);
        return letter;
    }

    private void setDefaultState() {
        setCreatedAt(Instant.now());
        setCreateUser(this.userId);
    }
}
