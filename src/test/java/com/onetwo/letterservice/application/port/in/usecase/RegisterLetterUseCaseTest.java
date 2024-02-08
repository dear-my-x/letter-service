package com.onetwo.letterservice.application.port.in.usecase;

import com.onetwo.letterservice.application.port.in.command.RegisterLetterCommand;
import com.onetwo.letterservice.application.port.in.response.RegisterLetterResponseDto;
import com.onetwo.letterservice.application.port.out.RegisterLetterPort;
import com.onetwo.letterservice.application.service.converter.LetterUseCaseConverter;
import com.onetwo.letterservice.application.service.service.LetterService;
import com.onetwo.letterservice.domain.Letter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RegisterLetterUseCaseTest {

    @InjectMocks
    private LetterService registerLetterUseCase;

    @Mock
    private RegisterLetterPort registerLetterPort;

    @Mock
    private LetterUseCaseConverter letterUseCaseConverter;

    private final String userId = "testUserId";
    private final String receiverUserId = "testReceiverUserId";
    private final String content = "letterContent";

    @Test
    @DisplayName("[단위][Use Case] Letter 등록 - 성공 테스트")
    void registerLetterUseCaseSuccessTest() {
        //given
        RegisterLetterCommand registerLetterCommand = new RegisterLetterCommand(userId, receiverUserId, content);

        RegisterLetterResponseDto registerLetterResponseDto = new RegisterLetterResponseDto(true);

        Letter savedLetter = Letter.createNewLetterByCommand(registerLetterCommand);

        given(registerLetterPort.registerLetter(any(Letter.class))).willReturn(savedLetter);
        given(letterUseCaseConverter.letterToRegisterResponseDto(any(Letter.class))).willReturn(registerLetterResponseDto);
        //when
        RegisterLetterResponseDto result = registerLetterUseCase.registerLetter(registerLetterCommand);

        //then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isRegisterSuccess());
    }
}