package com.colegiominayticha.ccplgdmtlogicalfolder;

import com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.constant.HeaderConstant;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.consumer.LogicalFolderInformation;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.consumer.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.consumer.LogicalFolderResponseDto;

import java.time.LocalDateTime;
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
        return LogicalFolderRequestDto
                .builder()
                .build();
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
