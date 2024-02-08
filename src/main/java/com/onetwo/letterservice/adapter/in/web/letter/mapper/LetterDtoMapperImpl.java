package com.onetwo.letterservice.adapter.in.web.letter.mapper;

import com.onetwo.letterservice.adapter.in.web.letter.request.RegisterLetterRequest;
import com.onetwo.letterservice.adapter.in.web.letter.response.DeleteLetterResponse;
import com.onetwo.letterservice.adapter.in.web.letter.response.RegisterLetterResponse;
import com.onetwo.letterservice.application.port.in.command.DeleteLetterCommand;
import com.onetwo.letterservice.application.port.in.command.RegisterLetterCommand;
import com.onetwo.letterservice.application.port.in.response.DeleteLetterResponseDto;
import com.onetwo.letterservice.application.port.in.response.RegisterLetterResponseDto;
import org.springframework.stereotype.Component;

@Component
public class LetterDtoMapperImpl implements LetterDtoMapper {
    @Override
    public RegisterLetterCommand registerRequestToCommand(String userId, RegisterLetterRequest registerLetterRequest) {
        return new RegisterLetterCommand(userId, registerLetterRequest.receiverUserId(), registerLetterRequest.content());
    }

    @Override
    public RegisterLetterResponse dtoToRegisterResponse(RegisterLetterResponseDto registerLetterResponseDto) {
        return new RegisterLetterResponse(registerLetterResponseDto.isRegisterSuccess());
    }

    @Override
    public DeleteLetterCommand deleteRequestToCommand(Long letterId, String userId) {
        return new DeleteLetterCommand(letterId, userId);
    }

    @Override
    public DeleteLetterResponse dtoToDeleteResponse(DeleteLetterResponseDto deleteLetterResponseDto) {
        return new DeleteLetterResponse(deleteLetterResponseDto.isDeleteSuccess());
    }
}
