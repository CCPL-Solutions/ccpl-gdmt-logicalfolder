package com.colegiominayticha.ccplgdmtlogicalfolder.controller;

import com.ccplsolutions.common.model.RestRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.api.LogicalFoldersApi;
import com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.constant.PathParamConstant;
import com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.util.Util;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.FolderUpdateRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderResponseDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.service.ILogicalFolderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

        RestRequestDto<LogicalFolderRequestDto> restConsumerRequest =
                prepareRequestCreateLogicalFolder(xUserId, logicalFolderRequestDto);

        return ResponseEntity.ok(service.createLogicalFolder(restConsumerRequest));
    }

    @Override
    @PreAuthorize("hasRole('logicalfolders:logicalfolders:read')")
    public ResponseEntity<LogicalFolderResponseDto> findLogicalFolderById(String xUserId, String logicalFolderId) {

        RestRequestDto<Void> restConsumerRequest =
                prepareRequestFindLogicalFolderById(xUserId, logicalFolderId);

        return ResponseEntity.ok(service.findLogicalFolderById(restConsumerRequest));
    }

    @Override
    @PreAuthorize("hasRole('logicalfolders:logicalfolders:write')")
    public ResponseEntity<Void> updateLogicalFolder(String xUserId, String logicalFolderId,
                                                    FolderUpdateRequestDto folderUpdateRequestDto) {

        RestRequestDto<FolderUpdateRequestDto> restConsumerRequest =
                prepareRequestUpdateLogicalFolder(xUserId, logicalFolderId, folderUpdateRequestDto);

        service.updateLogicalFolder(restConsumerRequest);

        return ResponseEntity.ok().build();
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

    private RestRequestDto<Void> prepareRequestFindLogicalFolderById(
            String xUserId, String logicalFolderId) {

        Map<String, Object> headers = Util.getHeaders(xUserId);
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put(PathParamConstant.LOGICAL_FOLDER_ID, logicalFolderId);

        return RestRequestDto
                .<Void>builder()
                .headers(headers)
                .pathParams(pathParams)
                .build();
    }

    private RestRequestDto<FolderUpdateRequestDto> prepareRequestUpdateLogicalFolder(
            String xUserId, String logicalFolderId, FolderUpdateRequestDto folderUpdateRequestDto) {

        Map<String, Object> headers = Util.getHeaders(xUserId);
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put(PathParamConstant.LOGICAL_FOLDER_ID, logicalFolderId);

        return RestRequestDto
                .<FolderUpdateRequestDto>builder()
                .body(folderUpdateRequestDto)
                .headers(headers)
                .pathParams(pathParams)
                .build();
    }
}
