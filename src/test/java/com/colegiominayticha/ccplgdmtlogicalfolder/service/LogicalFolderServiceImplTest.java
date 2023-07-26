package com.colegiominayticha.ccplgdmtlogicalfolder.service;

import com.ccplsolutions.common.model.RestRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.colegiominayticha.ccplgdmtlogicalfolder.DummyMock.getLogicalFolderRequestDto;
import static com.colegiominayticha.ccplgdmtlogicalfolder.DummyMock.getRequestHeaders;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class LogicalFolderServiceImplTest {

    @Mock
    RestRequestDto<LogicalFolderRequestDto> restConsumerRequest;

    @InjectMocks
    private LogicalFolderServiceImpl service;

    @BeforeEach
    void setUp() {
        restConsumerRequest = RestRequestDto
                .<LogicalFolderRequestDto>builder()
                .headers(getRequestHeaders())
                .body(getLogicalFolderRequestDto())
                .build();
    }

    @Test
    void createLogicalFolderTest() {
        // Given
        // When
        LogicalFolderResponseDto response = service.createLogicalFolder(restConsumerRequest);
        // Then
        assertNotNull(response);
    }
}