package com.colegiominayticha.ccplgdmtlogicalfolder.consumer;

import com.colegiominayticha.ccplgdmtlogicalfolder.model.consumer.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.service.ILogicalFolderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.colegiominayticha.ccplgdmtlogicalfolder.DummyMock.getLogicalFolderRequestDto;
import static com.colegiominayticha.ccplgdmtlogicalfolder.DummyMock.getLogicalFolderResponseDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(LogicalFolderController.class)
class LogicalFolderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ILogicalFolderService service;

    @Test
    @WithMockUser(roles = "clients:orders:read")
    void createLogicalFolderTest() throws Exception {
        // Given
        LogicalFolderRequestDto request = getLogicalFolderRequestDto();

        // When
        when(service.createLogicalFolder(any())).thenReturn(getLogicalFolderResponseDto());
        mockMvc.perform(post("/api/v1/logical-folders")
                        .header("x-user-id", "pedro.chavez")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Then
        verify(service).createLogicalFolder(any());

    }
}