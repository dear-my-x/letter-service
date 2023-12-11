package com.onetwo.letterservice.application.port.in.command;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class RegisterLetterCommandValidationTest {

    private final String userId = "testUserId";
    private final String targetUserId = "testTargetUserId";
    private final String content = "letterContent";

    @Test
    @DisplayName("[단위][Command Validation] Register Letter Command Validation test - 성공 테스트")
    void registerLetterCommandValidationTest() {
        //given when then
        Assertions.assertDoesNotThrow(() -> new RegisterLetterCommand(userId, targetUserId, content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("[단위][Command Validation] Register Letter Command user Id Validation fail test - 실패 테스트")
    void registerLetterCommandUserIdValidationFailTest(String testUserId) {
        //given when then
        Assertions.assertThrows(ConstraintViolationException.class, () -> new RegisterLetterCommand(testUserId, targetUserId, content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("[단위][Command Validation] Register Letter Command targetUserId Validation fail test - 실패 테스트")
    void registerLetterCommandTargetUserIdValidationFailTest(String testTargetUserId) {
        //given when then
        Assertions.assertThrows(ConstraintViolationException.class, () -> new RegisterLetterCommand(userId, testTargetUserId, content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("[단위][Command Validation] Register Letter Command user Id Validation fail test - 실패 테스트")
    void registerLetterCommandContentValidationFailTest(String testContent) {
        //given when then
        Assertions.assertThrows(ConstraintViolationException.class, () -> new RegisterLetterCommand(userId, targetUserId, testContent));
    }
}