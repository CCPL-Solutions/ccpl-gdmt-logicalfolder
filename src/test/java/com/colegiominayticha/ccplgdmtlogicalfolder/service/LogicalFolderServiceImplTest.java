package com.colegiominayticha.ccplgdmtlogicalfolder.service;

import com.colegiominayticha.ccplgdmtlogicalfolder.DummyMock;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.RestConsumerRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.consumer.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.consumer.LogicalFolderResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class LogicalFolderServiceImplTest {

    @Mock
    RestConsumerRequestDto<LogicalFolderRequestDto> restConsumerRequest;

    @InjectMocks
    private LogicalFolderServiceImpl service;

    @BeforeEach
    void setUp() {
        restConsumerRequest = RestConsumerRequestDto
                .<LogicalFolderRequestDto>builder()
                .headers(DummyMock.getRequestHeaders())
                .body(DummyMock.getLogicalFolderRequestDto())
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