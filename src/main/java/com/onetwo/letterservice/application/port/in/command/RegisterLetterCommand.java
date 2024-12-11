package com.onetwo.letterservice.application.port.in.command;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import onetwo.mailboxcommonconfig.common.SelfValidating;

@Getter
public final class RegisterLetterCommand extends SelfValidating<RegisterLetterCommand> {

    @NotEmpty
    private final String userId;

    @NotEmpty
    private final String receiverUserId;

    @NotEmpty
    private final String content;

    public RegisterLetterCommand(String userId, String receiverUserId, String content) {
        this.userId = userId;
        this.receiverUserId = receiverUserId;
        this.content = content;
        this.validateSelf();
    }
}
