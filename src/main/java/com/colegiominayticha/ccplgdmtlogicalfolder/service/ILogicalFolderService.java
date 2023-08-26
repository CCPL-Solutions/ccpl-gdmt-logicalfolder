package com.colegiominayticha.ccplgdmtlogicalfolder.service;

import com.ccplsolutions.common.model.RestRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.FolderUpdateRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderResponseDto;

public interface ILogicalFolderService {

    LogicalFolderResponseDto createLogicalFolder(RestRequestDto<LogicalFolderRequestDto> restConsumerRequest);

    LogicalFolderResponseDto findLogicalFolderById(RestRequestDto<Void> restConsumerRequest);

    void updateLogicalFolder(RestRequestDto<FolderUpdateRequestDto> restConsumerRequest);
}
