package com.colegiominayticha.ccplgdmtlogicalfolder.service;

import com.colegiominayticha.ccplgdmtlogicalfolder.model.RestConsumerRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.consumer.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.consumer.LogicalFolderResponseDto;

public interface ILogicalFolderService {

    LogicalFolderResponseDto createLogicalFolder(RestConsumerRequestDto<LogicalFolderRequestDto> restConsumerRequest);

}
