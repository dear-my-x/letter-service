package com.onetwo.letterservice.adapter.in.web.letter.request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterLetterRequest(@NotEmpty String targetUserId,
                                    @NotEmpty String content) {
}
