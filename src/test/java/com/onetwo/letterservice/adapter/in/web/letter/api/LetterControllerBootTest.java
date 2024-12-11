package com.onetwo.letterservice.adapter.in.web.letter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onetwo.letterservice.adapter.in.web.config.TestHeader;
import com.onetwo.letterservice.adapter.in.web.letter.request.RegisterLetterRequest;
import com.onetwo.letterservice.application.port.in.command.RegisterLetterCommand;
import com.onetwo.letterservice.application.port.out.RegisterLetterPort;
import com.onetwo.letterservice.common.GlobalStatus;
import com.onetwo.letterservice.common.GlobalUrl;
import com.onetwo.letterservice.domain.Letter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(TestHeader.class)
class LetterControllerBootTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RegisterLetterPort registerLetterPort;

    @Autowired
    private TestHeader testHeader;

    private final String userId = "testUserId";
    private final String receiverUserId = "testReceiverUserId";
    private final String content = "letterContent";

    @Test
    @Transactional
    @DisplayName("[통합][Web Adapter] Letter 등록 - 성공 테스트")
    void registerLetterSuccessTest() throws Exception {
        //given
        RegisterLetterRequest registerLetterRequest = new RegisterLetterRequest(receiverUserId, content);

        //when
        ResultActions resultActions = mockMvc.perform(
                post(GlobalUrl.LETTER_ROOT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerLetterRequest))
                        .headers(testHeader.getRequestHeaderWithMockAccessKey(userId))
                        .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isCreated())
                .andDo(print())
                .andDo(document("register-letter",
                                requestHeaders(
                                        headerWithName(GlobalStatus.ACCESS_ID).description("서버 Access id"),
                                        headerWithName(GlobalStatus.ACCESS_KEY).description("서버 Access key"),
                                        headerWithName(GlobalStatus.ACCESS_TOKEN).description("유저의 access-token")
                                ),
                                requestFields(
                                        fieldWithPath("receiverUserId").type(JsonFieldType.STRING).description("편지 발송 대상 user id"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("편지 본문")
                                ),
                                responseFields(
                                        fieldWithPath("isRegisterSuccess").type(JsonFieldType.BOOLEAN).description("등록 완료 여부")
                                )
                        )
                );
    }

    @Test
    @Transactional
    @DisplayName("[통합][Web Adapter] Letter 삭제 - 성공 테스트")
    void deleteLetterSuccessTest() throws Exception {
        //given
        RegisterLetterCommand registerLetterCommand = new RegisterLetterCommand(userId, receiverUserId, content);
        Letter letter = Letter.createNewLetterByCommand(registerLetterCommand);

        Letter savedLetter = registerLetterPort.registerLetter(letter);

        Long letterId = savedLetter.getId();

        //when
        ResultActions resultActions = mockMvc.perform(
                delete(GlobalUrl.LETTER_ROOT + GlobalUrl.PATH_VARIABLE_LETTER_ID_WITH_BRACE, letterId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(testHeader.getRequestHeaderWithMockAccessKey(userId))
                        .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("delete-letter",
                                requestHeaders(
                                        headerWithName(GlobalStatus.ACCESS_ID).description("서버 Access id"),
                                        headerWithName(GlobalStatus.ACCESS_KEY).description("서버 Access key"),
                                        headerWithName(GlobalStatus.ACCESS_TOKEN).description("유저의 access-token")
                                ),
                                pathParameters(
                                        parameterWithName(GlobalUrl.PATH_VARIABLE_LETTER_ID).description("삭제할 letter id")
                                ),
                                responseFields(
                                        fieldWithPath("isDeleteSuccess").type(JsonFieldType.BOOLEAN).description("삭제 완료 여부")
                                )
                        )
                );
    }

    @Test
    @Transactional
    @DisplayName("[통합][Web Adapter] Letter 수신인 삭제 - 성공 테스트")
    void deleteLetterFromReceiverSuccessTest() throws Exception {
        //given
        RegisterLetterCommand registerLetterCommand = new RegisterLetterCommand(userId, receiverUserId, content);
        Letter letter = Letter.createNewLetterByCommand(registerLetterCommand);

        Letter savedLetter = registerLetterPort.registerLetter(letter);

        Long letterId = savedLetter.getId();

        //when
        ResultActions resultActions = mockMvc.perform(
                delete(GlobalUrl.LETTER_ROOT + GlobalUrl.PATH_VARIABLE_LETTER_ID_WITH_BRACE, letterId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(testHeader.getRequestHeaderWithMockAccessKey(receiverUserId))
                        .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }
}