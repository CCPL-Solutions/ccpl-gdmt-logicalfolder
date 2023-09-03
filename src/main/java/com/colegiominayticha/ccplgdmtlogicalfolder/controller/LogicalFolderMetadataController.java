package com.colegiominayticha.ccplgdmtlogicalfolder.controller;

import com.colegiominayticha.ccplgdmtlogicalfolder.api.LogicalFoldersApi;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.SpecificMetadataDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.service.ILogicalFolderMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LogicalFolderMetadataController implements LogicalFoldersApi {

    @Autowired
    private ILogicalFolderMetadataService service;

    @Override
    @PreAuthorize("hasRole('logicalfolders:specificmetadata:write')")
    public ResponseEntity<Void> addingSpecificMetadataToFolder(String xUserId, String logicalFolderId,
                                                               SpecificMetadataDto specificMetadataDto) {
        // TODO: Pending Implementation
        return LogicalFoldersApi.super.addingSpecificMetadataToFolder(xUserId, logicalFolderId, specificMetadataDto);
    }

    @Override
    @PreAuthorize("hasRole('logicalfolders:specificmetadata:delete')")
    public ResponseEntity<Void> deletSpecificMetadata(String xUserId, String logicalFolderId, String metadataId) {
        // TODO: Pending Implementation
        return LogicalFoldersApi.super.deletSpecificMetadata(xUserId, logicalFolderId, metadataId);
    }
}
