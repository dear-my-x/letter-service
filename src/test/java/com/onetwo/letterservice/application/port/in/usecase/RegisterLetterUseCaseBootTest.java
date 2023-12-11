package com.onetwo.letterservice.application.port.in.usecase;

import com.onetwo.letterservice.application.port.in.command.RegisterLetterCommand;
import com.onetwo.letterservice.application.port.in.response.RegisterLetterResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RegisterLetterUseCaseBootTest {

    @Autowired
    private RegisterLetterUseCase registerLetterUseCase;

    private final String userId = "testUserId";
    private final String targetUserId = "testTargetUserId";
    private final String content = "letterContent";

    @Test
    @DisplayName("[단위][Use Case] Letter 등록 - 성공 테스트")
    void registerLetterUseCaseSuccessTest() {
        //given
        RegisterLetterCommand registerLetterCommand = new RegisterLetterCommand(userId, targetUserId, content);

        //when
        RegisterLetterResponseDto result = registerLetterUseCase.registerLetter(registerLetterCommand);

        //then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isRegisterSuccess());
    }
}