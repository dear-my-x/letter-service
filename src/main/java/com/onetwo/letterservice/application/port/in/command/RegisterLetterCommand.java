package com.onetwo.letterservice.application.port.in.command;

import com.onetwo.letterservice.application.port.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public final class RegisterLetterCommand extends SelfValidating<RegisterLetterCommand> {

    @NotEmpty
    private final String userId;

    @NotEmpty
    private final String targetUserId;

    @NotEmpty
    private final String content;

    public RegisterLetterCommand(String userId, String targetUserId, String content) {
        this.userId = userId;
        this.targetUserId = targetUserId;
        this.content = content;
        this.validateSelf();
    }
}
