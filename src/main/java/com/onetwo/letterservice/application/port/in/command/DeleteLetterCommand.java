package com.onetwo.letterservice.application.port.in.command;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import onetwo.mailboxcommonconfig.common.SelfValidating;

@Getter
public final class DeleteLetterCommand extends SelfValidating<DeleteLetterCommand> {

    @NotNull
    private final Long letterId;

    @NotEmpty
    private final String userId;

    public DeleteLetterCommand(Long letterId, String userId) {
        this.letterId = letterId;
        this.userId = userId;
        this.validateSelf();
    }
}
