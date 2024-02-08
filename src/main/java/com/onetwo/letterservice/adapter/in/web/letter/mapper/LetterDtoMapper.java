package com.onetwo.letterservice.adapter.in.web.letter.mapper;

import com.onetwo.letterservice.adapter.in.web.letter.request.RegisterLetterRequest;
import com.onetwo.letterservice.adapter.in.web.letter.response.DeleteLetterResponse;
import com.onetwo.letterservice.adapter.in.web.letter.response.RegisterLetterResponse;
import com.onetwo.letterservice.application.port.in.command.DeleteLetterCommand;
import com.onetwo.letterservice.application.port.in.command.RegisterLetterCommand;
import com.onetwo.letterservice.application.port.in.response.DeleteLetterResponseDto;
import com.onetwo.letterservice.application.port.in.response.RegisterLetterResponseDto;

public interface LetterDtoMapper {
    RegisterLetterCommand registerRequestToCommand(String userId, RegisterLetterRequest registerLetterRequest);

    RegisterLetterResponse dtoToRegisterResponse(RegisterLetterResponseDto registerLetterResponseDto);

    DeleteLetterCommand deleteRequestToCommand(Long letterId, String userId);

    DeleteLetterResponse dtoToDeleteResponse(DeleteLetterResponseDto deleteLetterResponseDto);
}
