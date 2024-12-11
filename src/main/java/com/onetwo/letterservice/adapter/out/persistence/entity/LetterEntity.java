package com.onetwo.letterservice.adapter.out.persistence.entity;

import com.onetwo.letterservice.adapter.out.persistence.repository.converter.BooleanNumberConverter;
import com.onetwo.letterservice.domain.Letter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@Table(name = "letter")
public class LetterEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String receiverUserId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, length = 1)
    @Convert(converter = BooleanNumberConverter.class)
    private Boolean arrived;

    @Column(nullable = false, length = 1)
    @Convert(converter = BooleanNumberConverter.class)
    private Boolean received;

    @Column(nullable = false, length = 1)
    @Convert(converter = BooleanNumberConverter.class)
    private Boolean state;

    @Column(nullable = false, length = 1)
    @Convert(converter = BooleanNumberConverter.class)
    private Boolean receiverState;

    private LetterEntity(Long id, String userId, String receiverUserId, String content, Boolean arrived, Boolean received, Boolean state, Boolean receiverState) {
        this.id = id;
        this.userId = userId;
        this.receiverUserId = receiverUserId;
        this.content = content;
        this.arrived = arrived;
        this.received = received;
        this.state = state;
        this.receiverState = receiverState;
    }

    public static LetterEntity domainToEntity(Letter letter) {
        LetterEntity letterEntity = new LetterEntity(
                letter.getId(),
                letter.getUserId(),
                letter.getReceiverUserId(),
                letter.getContent(),
                letter.getArrived(),
                letter.getReceived(),
                letter.getState(),
                letter.getReceiverState()
        );

        letterEntity.setMetaDataByDomain(letter);
        return letterEntity;
    }
}
