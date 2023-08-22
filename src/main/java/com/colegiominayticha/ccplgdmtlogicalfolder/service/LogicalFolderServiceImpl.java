package com.colegiominayticha.ccplgdmtlogicalfolder.service;

import com.ccplsolutions.common.exception.classification.business.InvalidDataException;
import com.ccplsolutions.common.model.RestRequestDto;
import com.ccplsolutions.security.service.AuthenticationService;
import com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.constant.MessageErrorEnum;
import com.colegiominayticha.ccplgdmtlogicalfolder.dataprovider.jpa.entity.LogicalFolderEntity;
import com.colegiominayticha.ccplgdmtlogicalfolder.dataprovider.jpa.repository.ILogicalFolderRepository;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderResponseDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.service.mapper.ILogicalFolderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class LogicalFolderServiceImpl implements ILogicalFolderService {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ILogicalFolderMapper mapper;

    @Autowired
    private ILogicalFolderRepository logicalFolderRepository;

    @Override
    public LogicalFolderResponseDto createLogicalFolder(
            RestRequestDto<LogicalFolderRequestDto> restRequest) {

        log.info("Entered /logical-folders Service");
        log.debug("Request Create Logical Folder: {}", restRequest);

        var requestBody = restRequest.getBody();
        var requestHeaders = restRequest.getHeaders();

        validateExistingFolder(requestBody);

        var logicalFolderEntityParent = this.findLogicalFolderById(requestBody.getParentId());
        var logicalFolderEntity = mapper.mapInCreateLogicalFolder(requestBody, requestHeaders, logicalFolderEntityParent);
        var logicalFolderEntitySaved = this.saveLogicalFolder(logicalFolderEntity);

        LogicalFolderResponseDto logicalFolderResponse = mapper.mapOutCreateLogicalFolder(logicalFolderEntitySaved);

        log.debug("Response Create Logical Folder: {}", logicalFolderResponse);

        return logicalFolderResponse;
    }

    private void validateExistingFolder(LogicalFolderRequestDto requestBody) {
        Optional<LogicalFolderEntity> logicalFolder = logicalFolderRepository.findByName(requestBody.getName());
        if (logicalFolder.isPresent()) {
            throw new InvalidDataException(
                    MessageErrorEnum.GDMT000.getDescription(),
                    MessageErrorEnum.GDMT000.getCode());
        }
    }

    private LogicalFolderEntity findLogicalFolderById(UUID logicalFolderId) {
        return logicalFolderRepository.findById(logicalFolderId)
                .orElseThrow(() -> new InvalidDataException(
                        MessageErrorEnum.GDMT001.getDescription(),
                        MessageErrorEnum.GDMT001.getCode()));
    }

    private LogicalFolderEntity saveLogicalFolder(LogicalFolderEntity logicalFolderEntity) {
        return logicalFolderRepository.save(logicalFolderEntity);
    }

}
