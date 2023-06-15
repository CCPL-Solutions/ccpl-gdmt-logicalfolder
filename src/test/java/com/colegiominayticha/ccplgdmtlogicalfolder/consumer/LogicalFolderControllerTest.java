package com.colegiominayticha.ccplgdmtlogicalfolder.consumer;

import com.colegiominayticha.ccplgdmtlogicalfolder.model.consumer.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.service.ILogicalFolderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.colegiominayticha.ccplgdmtlogicalfolder.DummyMock.getLogicalFolderRequestDto;
import static com.colegiominayticha.ccplgdmtlogicalfolder.DummyMock.getLogicalFolderResponseDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
@ContextConfiguration(classes = LogicalFolderController.class)
@ActiveProfiles("test")
class LogicalFolderControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ILogicalFolderService service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = {"gdmt:logicalfolders:logicalfolders:write"})
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