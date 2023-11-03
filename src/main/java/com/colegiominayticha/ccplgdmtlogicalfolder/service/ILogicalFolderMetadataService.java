package com.colegiominayticha.ccplgdmtlogicalfolder.service;

import com.ccplsolutions.common.model.RestRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.SpecificMetadataDto;

public interface ILogicalFolderMetadataService {

    void addingSpecificMetadataToFolder(RestRequestDto<SpecificMetadataDto> restConsumerRequest);

    void deleteSpecificMetadata(RestRequestDto<Void> restConsumerRequest);

}