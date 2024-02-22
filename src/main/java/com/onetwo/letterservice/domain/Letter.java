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

    private String receiverUserId;

    private String content;

    private Boolean arrived;

    private Boolean received;

    private Boolean state;

    private Boolean receiverState;

    public static Letter createNewLetterByCommand(RegisterLetterCommand registerLetterCommand) {
        Letter letter = new Letter(
                null,
                registerLetterCommand.getUserId(),
                registerLetterCommand.getReceiverUserId(),
                registerLetterCommand.getContent(),
                false,
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
                letterEntity.getReceiverUserId(),
                letterEntity.getContent(),
                letterEntity.getArrived(),
                letterEntity.getReceived(),
                letterEntity.getState(),
                letterEntity.getReceiverState()
        );

        letter.setMetaDataByEntity(letterEntity);
        return letter;
    }

    private void setDefaultState() {
        setCreatedAt(Instant.now());
        setCreateUser(this.userId);
    }

    private void setUpdateState() {
        setUpdatedAt(Instant.now());
        setUpdateUser(this.userId);
    }

    public boolean isSender(String userId) {
        return this.userId.equals(userId);
    }

    public boolean isReceiver(String userId) {
        return this.receiverUserId.equals(userId);
    }

    public void senderDelete() {
        this.state = true;
        setUpdatedAt(Instant.now());
        setUpdateUser(this.userId);
    }

    public void receiverDelete() {
        this.receiverState = true;
        setUpdatedAt(Instant.now());
        setUpdateUser(this.receiverUserId);
    }

    public boolean isDeletedFromSender() {
        return this.state;
    }

    public boolean isDeletedFromReceiver() {
        return this.receiverState;
    }
}
