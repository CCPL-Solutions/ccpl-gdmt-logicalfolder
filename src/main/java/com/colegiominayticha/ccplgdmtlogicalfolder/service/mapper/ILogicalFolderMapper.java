package com.colegiominayticha.ccplgdmtlogicalfolder.service.mapper;

import com.colegiominayticha.ccplgdmtlogicalfolder.dataprovider.jpa.entity.LogicalFolderEntity;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderResponseDto;

import java.util.Map;

public interface ILogicalFolderMapper {

    LogicalFolderEntity mapInCreateLogicalFolder(LogicalFolderRequestDto requestBody,
                                                 Map<String, Object> requestHeaders,
                                                 LogicalFolderEntity logicalFolderEntityParent);

    LogicalFolderResponseDto mapOutCreateLogicalFolder(LogicalFolderEntity logicalFolderEntitySaved);

}
