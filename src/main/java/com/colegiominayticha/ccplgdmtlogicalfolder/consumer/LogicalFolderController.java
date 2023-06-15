package com.colegiominayticha.ccplgdmtlogicalfolder.consumer;

import com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.util.Util;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.RestConsumerRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.consumer.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.consumer.LogicalFolderResponseDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.service.ILogicalFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class LogicalFolderController implements LogicalFoldersApi {

    @Autowired
    private ILogicalFolderService service;

    @Override
    @PreAuthorize("hasRole('gdmt:logicalfolders:logicalfolders:write')")
    public ResponseEntity<LogicalFolderResponseDto> createLogicalFolder(String xUserId, LogicalFolderRequestDto
            logicalFolderRequestDto) {

        RestConsumerRequestDto<LogicalFolderRequestDto> restConsumerRequest =
                prepareRestConsumerRequestCreateLogicalFolder(xUserId, logicalFolderRequestDto);

        return ResponseEntity.ok(service.createLogicalFolder(restConsumerRequest));
    }

    private RestConsumerRequestDto<LogicalFolderRequestDto> prepareRestConsumerRequestCreateLogicalFolder(
            String xUserId, LogicalFolderRequestDto logicalFolderRequestDto) {

        Map<String, Object> headers = Util.getHeaders(xUserId);

        return RestConsumerRequestDto
                .<LogicalFolderRequestDto>builder()
                .body(logicalFolderRequestDto)
                .headers(headers)
                .build();
    }
}
