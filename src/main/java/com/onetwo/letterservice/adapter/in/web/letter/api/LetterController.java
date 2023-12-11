package com.onetwo.letterservice.adapter.in.web.letter.api;

import com.onetwo.letterservice.adapter.in.web.letter.mapper.LetterDtoMapper;
import com.onetwo.letterservice.adapter.in.web.letter.request.RegisterLetterRequest;
import com.onetwo.letterservice.adapter.in.web.letter.response.RegisterLetterResponse;
import com.onetwo.letterservice.application.port.in.command.RegisterLetterCommand;
import com.onetwo.letterservice.application.port.in.response.RegisterLetterResponseDto;
import com.onetwo.letterservice.application.port.in.usecase.RegisterLetterUseCase;
import com.onetwo.letterservice.common.GlobalUrl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LetterController {

    private final RegisterLetterUseCase registerLetterUseCase;
    private final LetterDtoMapper letterDtoMapper;

    /**
     * Register Letter inbound adapter
     *
     * @param registerLetterRequest data about register letter
     * @param userId                user authentication id
     * @return Boolean about register success
     */
    @PostMapping(GlobalUrl.LETTER_ROOT)
    public ResponseEntity<RegisterLetterResponse> registerLetter(@RequestBody @Valid RegisterLetterRequest registerLetterRequest,
                                                                 @AuthenticationPrincipal String userId) {
        RegisterLetterCommand registerLetterCommand = letterDtoMapper.registerRequestToCommand(userId, registerLetterRequest);
        RegisterLetterResponseDto registerLetterResponseDto = registerLetterUseCase.registerLetter(registerLetterCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(letterDtoMapper.dtoToRegisterResponse(registerLetterResponseDto));
    }
}
