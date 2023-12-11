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
    private String targetUserId;

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

    private LetterEntity(Long id, String userId, String targetUserId, String content, Boolean arrived, Boolean received, Boolean state) {
        this.id = id;
        this.userId = userId;
        this.targetUserId = targetUserId;
        this.content = content;
        this.arrived = arrived;
        this.received = received;
        this.state = state;
    }

    public static LetterEntity domainToEntity(Letter letter) {
        LetterEntity letterEntity = new LetterEntity(
                letter.getId(),
                letter.getUserId(),
                letter.getTargetUserId(),
                letter.getContent(),
                letter.getArrived(),
                letter.getReceived(),
                letter.getState()
        );

        letterEntity.setMetaDataByDomain(letter);
        return letterEntity;
    }
}
