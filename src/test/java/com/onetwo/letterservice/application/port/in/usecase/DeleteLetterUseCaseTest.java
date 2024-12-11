package com.onetwo.letterservice.application.port.in.usecase;

import com.onetwo.letterservice.application.port.in.command.DeleteLetterCommand;
import com.onetwo.letterservice.application.port.in.command.RegisterLetterCommand;
import com.onetwo.letterservice.application.port.in.response.DeleteLetterResponseDto;
import com.onetwo.letterservice.application.port.out.ReadLetterPort;
import com.onetwo.letterservice.application.port.out.UpdateLetterPort;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DeleteLetterUseCaseTest {

    @InjectMocks
    private LetterService deleteLetterUseCase;

    @Mock
    private ReadLetterPort readLetterPort;

    @Mock
    private UpdateLetterPort updateLetterPort;

    @Mock
    private LetterUseCaseConverter letterUseCaseConverter;

    private final String userId = "testUserId";
    private final String receiverUserId = "testReceiverUserId";
    private final String content = "letterContent";
    private final long letterId = 1L;

    @Test
    @DisplayName("[단위][Use Case] Letter 등록 - 성공 테스트")
    void deleteLetterUseCaseSuccessTest() {
        //given
        DeleteLetterCommand deleteLetterCommand = new DeleteLetterCommand(letterId, userId);
        DeleteLetterResponseDto deleteLetterResponseDto = new DeleteLetterResponseDto(true);

        RegisterLetterCommand registerLetterCommand = new RegisterLetterCommand(userId, receiverUserId, content);
        Letter letter = Letter.createNewLetterByCommand(registerLetterCommand);

        given(readLetterPort.findById(anyLong())).willReturn(Optional.of(letter));
        given(letterUseCaseConverter.letterToDeleteResponseDto(anyBoolean())).willReturn(deleteLetterResponseDto);
        //when
        DeleteLetterResponseDto result = deleteLetterUseCase.deleteLetter(deleteLetterCommand);

        //then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isDeleteSuccess());
    }
}