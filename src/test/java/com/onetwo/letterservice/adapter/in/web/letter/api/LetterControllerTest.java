package com.onetwo.letterservice.adapter.in.web.letter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onetwo.letterservice.adapter.in.web.config.TestConfig;
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
import onetwo.mailboxcommonconfig.common.MailBoxCommonAutoConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LetterController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                        MailBoxCommonAutoConfig.class
                })
        })
@Import(TestConfig.class)
class LetterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegisterLetterUseCase registerLetterUseCase;

    @MockBean
    private DeleteLetterUseCase deleteLetterUseCase;

    @MockBean
    private LetterDtoMapper letterDtoMapper;

    private final String userId = "testUserId";
    private final String receiverUserId = "testReceiverUserId";
    private final String content = "letterContent";
    private final Long letterId = 1L;

    @Test
    @WithMockUser(username = userId)
    @DisplayName("[단위][Web Adapter] Letter 등록 - 성공 테스트")
    void registerLetterSuccessTest() throws Exception {
        //given
        RegisterLetterRequest registerLetterRequest = new RegisterLetterRequest(receiverUserId, content);
        RegisterLetterCommand registerLetterCommand = new RegisterLetterCommand(userId, registerLetterRequest.receiverUserId(), registerLetterRequest.content());
        RegisterLetterResponseDto registerLetterResponseDto = new RegisterLetterResponseDto(true);
        RegisterLetterResponse registerLetterResponse = new RegisterLetterResponse(true);

        when(letterDtoMapper.registerRequestToCommand(anyString(), any(RegisterLetterRequest.class))).thenReturn(registerLetterCommand);
        when(registerLetterUseCase.registerLetter(any(RegisterLetterCommand.class))).thenReturn(registerLetterResponseDto);
        when(letterDtoMapper.dtoToRegisterResponse(any(RegisterLetterResponseDto.class))).thenReturn(registerLetterResponse);
        //when
        ResultActions resultActions = mockMvc.perform(
                post(GlobalUrl.LETTER_ROOT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerLetterRequest))
                        .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = userId)
    @DisplayName("[단위][Web Adapter] Letter 삭제 - 성공 테스트")
    void deleteLetterSuccessTest() throws Exception {
        //given
        DeleteLetterCommand deleteLetterCommand = new DeleteLetterCommand(letterId, userId);
        DeleteLetterResponseDto deleteLetterResponseDto = new DeleteLetterResponseDto(true);
        DeleteLetterResponse deleteLetterResponse = new DeleteLetterResponse(true);

        when(letterDtoMapper.deleteRequestToCommand(anyLong(), anyString())).thenReturn(deleteLetterCommand);
        when(deleteLetterUseCase.deleteLetter(any(DeleteLetterCommand.class))).thenReturn(deleteLetterResponseDto);
        when(letterDtoMapper.dtoToDeleteResponse(any(DeleteLetterResponseDto.class))).thenReturn(deleteLetterResponse);
        //when
        ResultActions resultActions = mockMvc.perform(
                delete(GlobalUrl.LETTER_ROOT + GlobalUrl.PATH_VARIABLE_LETTER_ID_WITH_BRACE, letterId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }
}