package com.onetwo.letterservice.adapter.in.web.letter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onetwo.letterservice.adapter.in.web.config.TestConfig;
import com.onetwo.letterservice.adapter.in.web.letter.mapper.LetterDtoMapper;
import com.onetwo.letterservice.adapter.in.web.letter.request.RegisterLetterRequest;
import com.onetwo.letterservice.application.port.in.usecase.DeleteLetterUseCase;
import com.onetwo.letterservice.application.port.in.usecase.RegisterLetterUseCase;
import com.onetwo.letterservice.common.GlobalUrl;
import com.onetwo.letterservice.common.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
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

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LetterController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                        SecurityConfig.class
                })
        })
@Import(TestConfig.class)
class LetterControllerValidationTest {

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

    @ParameterizedTest
    @NullAndEmptySource
    @WithMockUser(username = userId)
    @DisplayName("[단위][Web Adapter] Letter 등록 receiver user id validation fail - 실패 테스트")
    void registerLetterReceiverUserIdValidationFailTest(String testReceiverUserId) throws Exception {
        //given
        RegisterLetterRequest registerLetterRequest = new RegisterLetterRequest(testReceiverUserId, content);

        //when
        ResultActions resultActions = mockMvc.perform(
                post(GlobalUrl.LETTER_ROOT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerLetterRequest))
                        .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isBadRequest())
                .andDo(print());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @WithMockUser(username = userId)
    @DisplayName("[단위][Web Adapter] Letter 등록 content validation fail - 실패 테스트")
    void registerLetterContentValidationFailTest(String testContent) throws Exception {
        //given
        RegisterLetterRequest registerLetterRequest = new RegisterLetterRequest(receiverUserId, testContent);

        //when
        ResultActions resultActions = mockMvc.perform(
                post(GlobalUrl.LETTER_ROOT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerLetterRequest))
                        .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isBadRequest())
                .andDo(print());
    }

    @ParameterizedTest
    @NullSource
    @WithMockUser(username = userId)
    @DisplayName("[단위][Web Adapter] Letter 삭제 letter id validation fail - 실패 테스트")
    void deleteLetterLetterIdValidationFailTest(Long testLetterId) throws Exception {
        //given

        //when
        ResultActions resultActions = mockMvc.perform(
                delete(GlobalUrl.LETTER_ROOT + GlobalUrl.PATH_VARIABLE_LETTER_ID_WITH_BRACE, testLetterId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isNotFound())
                .andDo(print());
    }
}