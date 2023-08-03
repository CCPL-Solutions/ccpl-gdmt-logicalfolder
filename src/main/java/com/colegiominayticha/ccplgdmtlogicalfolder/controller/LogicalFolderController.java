package com.colegiominayticha.ccplgdmtlogicalfolder.controller;

import com.ccplsolutions.common.model.RestRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.api.LogicalFoldersApi;
import com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.util.Util;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderResponseDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.service.ILogicalFolderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class LogicalFolderController implements LogicalFoldersApi {

    @Autowired
    private ILogicalFolderService service;

    @Override
    @PreAuthorize("hasRole('logicalfolders:logicalfolders:write')")
    public ResponseEntity<LogicalFolderResponseDto> createLogicalFolder(String xUserId, LogicalFolderRequestDto
            logicalFolderRequestDto) {
        log.info("Entered /logical-folders Controller");

        RestRequestDto<LogicalFolderRequestDto> restConsumerRequest =
                prepareRequestCreateLogicalFolder(xUserId, logicalFolderRequestDto);

        return ResponseEntity.ok(service.createLogicalFolder(restConsumerRequest));
    }

    private RestRequestDto<LogicalFolderRequestDto> prepareRequestCreateLogicalFolder(
            String xUserId, LogicalFolderRequestDto logicalFolderRequestDto) {

        Map<String, Object> headers = Util.getHeaders(xUserId);

        return RestRequestDto
                .<LogicalFolderRequestDto>builder()
                .body(logicalFolderRequestDto)
                .headers(headers)
                .build();
    }
}
