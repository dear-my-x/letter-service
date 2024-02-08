package com.onetwo.letterservice.adapter.in.web.letter.api;

import com.onetwo.letterservice.adapter.in.web.letter.mapper.LetterDtoMapper;
import com.onetwo.letterservice.adapter.in.web.letter.request.RegisterLetterRequest;
import com.onetwo.letterservice.adapter.in.web.letter.response.DeleteLetterResponse;
import com.onetwo.letterservice.adapter.in.web.letter.response.RegisterLetterResponse;
import com.onetwo.letterservice.application.port.in.command.DeleteLetterCommand;
import com.onetwo.letterservice.application.port.in.command.RegisterLetterCommand;
import com.onetwo.letterservice.application.port.in.response.DeleteLetterResponseDto;
import com.onetwo.letterservice.application.port.in.response.RegisterLetterResponseDto;
import com.onetwo.letterservice.application.port.in.usecase.DeleteLetterUseCase;
import com.onetwo.letterservice.application.port.in.usecase.RegisterLetterUseCase;
import com.onetwo.letterservice.common.GlobalUrl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LetterController {

    private final RegisterLetterUseCase registerLetterUseCase;
    private final DeleteLetterUseCase deleteLetterUseCase;
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

    /**
     * Delete Letter inbound adapter
     *
     * @param letterId request delete letter's id
     * @param userId   user authentication id
     * @return Boolean about delete letter success
     */
    @DeleteMapping(GlobalUrl.LETTER_ROOT + GlobalUrl.PATH_VARIABLE_LETTER_ID_WITH_BRACE)
    public ResponseEntity<DeleteLetterResponse> deleteLetter(@PathVariable(GlobalUrl.PATH_VARIABLE_LETTER_ID) Long letterId,
                                                             @AuthenticationPrincipal String userId) {
        DeleteLetterCommand deleteLetterCommand = letterDtoMapper.deleteRequestToCommand(letterId, userId);
        DeleteLetterResponseDto deleteLetterResponseDto = deleteLetterUseCase.deleteLetter(deleteLetterCommand);
        return ResponseEntity.ok().body(letterDtoMapper.dtoToDeleteResponse(deleteLetterResponseDto));
    }
}
