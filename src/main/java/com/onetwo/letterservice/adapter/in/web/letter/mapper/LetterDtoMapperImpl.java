package com.onetwo.letterservice.adapter.in.web.letter.mapper;

import com.onetwo.letterservice.adapter.in.web.letter.request.RegisterLetterRequest;
import com.onetwo.letterservice.adapter.in.web.letter.response.RegisterLetterResponse;
import com.onetwo.letterservice.application.port.in.command.RegisterLetterCommand;
import com.onetwo.letterservice.application.port.in.response.RegisterLetterResponseDto;
import org.springframework.stereotype.Component;

@Component
public class LetterDtoMapperImpl implements LetterDtoMapper {
    @Override
    public RegisterLetterCommand registerRequestToCommand(String userId, RegisterLetterRequest registerLetterRequest) {
        return new RegisterLetterCommand(userId, registerLetterRequest.targetUserId(), registerLetterRequest.content());
    }

    @Override
    public RegisterLetterResponse dtoToRegisterResponse(RegisterLetterResponseDto registerLetterResponseDto) {
        return new RegisterLetterResponse(registerLetterResponseDto.isRegisterSuccess());
    }
}
