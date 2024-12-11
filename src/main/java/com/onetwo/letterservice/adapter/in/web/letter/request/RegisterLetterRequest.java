package com.onetwo.letterservice.adapter.in.web.letter.request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterLetterRequest(@NotEmpty String receiverUserId,
                                    @NotEmpty String content) {
}
