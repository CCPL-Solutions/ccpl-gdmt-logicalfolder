package com.colegiominayticha.ccplgdmtlogicalfolder;

import com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.constant.HeaderConstant;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderInformation;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DummyMock {

    public static Map<String, Object> getRequestHeaders() {
        Map<String, Object> headers = new HashMap<>();
        headers.put(HeaderConstant.X_USER_ID, "pedro.chavez");
        return headers;
    }

    public static LogicalFolderRequestDto getLogicalFolderRequestDto() {
        LogicalFolderRequestDto logicalFolderRequest = new LogicalFolderRequestDto();
        logicalFolderRequest.setName("name");
        logicalFolderRequest.setDescription("description");
        logicalFolderRequest.setParentId(UUID.randomUUID());
        logicalFolderRequest.setSpecificMetadata(new ArrayList<>());
        return logicalFolderRequest;

    }

    public static LogicalFolderResponseDto getLogicalFolderResponseDto() {
        return LogicalFolderResponseDto
                .builder()
                .id(UUID.randomUUID())
                .parentId(UUID.randomUUID())
                .folderInformation(getLogicalFolderInformation())
                .build();
    }

    public static LogicalFolderInformation getLogicalFolderInformation() {
        return LogicalFolderInformation
                .builder()
                .name("Actas")
                .createdUser("pedro.chavez")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
