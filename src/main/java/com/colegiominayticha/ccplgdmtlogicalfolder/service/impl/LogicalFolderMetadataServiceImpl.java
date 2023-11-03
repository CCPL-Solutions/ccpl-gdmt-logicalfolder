package com.colegiominayticha.ccplgdmtlogicalfolder.service.impl;

import com.ccplsolutions.common.model.RestRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.SpecificMetadataDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.service.ILogicalFolderMetadataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogicalFolderMetadataServiceImpl implements ILogicalFolderMetadataService {

    @Override
    public void addingSpecificMetadataToFolder(RestRequestDto<SpecificMetadataDto> restConsumerRequest) {

    }

    @Override
    public void deleteSpecificMetadata(RestRequestDto<Void> restConsumerRequest) {

    }

}