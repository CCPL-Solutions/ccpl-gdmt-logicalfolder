package com.colegiominayticha.ccplgdmtlogicalfolder.service;

import com.ccplsolutions.common.exception.classification.business.InvalidDataException;
import com.ccplsolutions.common.model.RestRequestDto;
import com.ccplsolutions.security.service.AuthenticationService;
import com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.constant.HeaderConstant;
import com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.constant.MessageErrorEnum;
import com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.constant.PathParamConstant;
import com.colegiominayticha.ccplgdmtlogicalfolder.dataprovider.jpa.entity.LogicalFolderEntity;
import com.colegiominayticha.ccplgdmtlogicalfolder.dataprovider.jpa.repository.ILogicalFolderRepository;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.FolderUpdateRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderResponseDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.service.mapper.ILogicalFolderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        log.info("Entered POST /logical-folders Service");
        log.debug("Request createLogicalFolder: {}", restRequest);

        var requestBody = restRequest.getBody();
        var requestHeaders = restRequest.getHeaders();

        validateExistingFolder(requestBody.getName());

        var logicalFolderEntityParent = this.findLogicalFolderEntityById(requestBody.getParentId());
        var logicalFolderEntity = mapper.mapInCreateLogicalFolder(requestBody, requestHeaders, logicalFolderEntityParent);
        var logicalFolderEntitySaved = this.saveLogicalFolder(logicalFolderEntity);

        var logicalFolderResponse = mapper.mapOutCreateLogicalFolder(logicalFolderEntitySaved);

        log.debug("Response createLogicalFolder: {}", logicalFolderResponse);

        return logicalFolderResponse;
    }

    @Override
    public LogicalFolderResponseDto findLogicalFolderById(RestRequestDto<Void> restConsumerRequest) {

        var logicalFolderId = UUID.fromString((String) restConsumerRequest.getPathParams()
                .get(PathParamConstant.LOGICAL_FOLDER_ID));

        log.info("Entered GET /logical-folders/{logical-folder-id}");
        log.debug("Request findLogicalFolderById: {}", logicalFolderId);

        var logicalFolderEntity = this.findLogicalFolderEntityById(logicalFolderId);
        var logicalFolderResponse = mapper.mapOutCreateLogicalFolder(logicalFolderEntity);

        log.debug("Response findLogicalFolderById: {}", logicalFolderResponse);
        return logicalFolderResponse;
    }

    @Override
    public void updateLogicalFolder(RestRequestDto<FolderUpdateRequestDto> restConsumerRequest) {

        log.info("Entered PUT /logical-folders/{logical-folder-id}");
        log.debug("Request updateLogicalFolder: {}", restConsumerRequest);

        var logicalFolderId = UUID.fromString((String) restConsumerRequest.getPathParams()
                .get(PathParamConstant.LOGICAL_FOLDER_ID));
        var requestBody = restConsumerRequest.getBody();
        var requestHeaders = restConsumerRequest.getHeaders();

        validateExistingFolder(requestBody.getName());

        var logicalFolderEntity = this.findLogicalFolderEntityById(logicalFolderId);
        logicalFolderEntity.setName(requestBody.getName());
        logicalFolderEntity.setDescription(requestBody.getDescription());
        logicalFolderEntity.setUpdatedUser((String) requestHeaders.get(HeaderConstant.X_USER_ID));
        logicalFolderEntity.setUpdatedAt(LocalDateTime.now());

        this.saveLogicalFolder(logicalFolderEntity);
    }

    private void validateExistingFolder(String logicalFolderName) {
        Optional<LogicalFolderEntity> logicalFolder = logicalFolderRepository.findByName(logicalFolderName);
        if (logicalFolder.isPresent()) {
            throw new InvalidDataException(
                    MessageErrorEnum.GDMT000.getDescription(),
                    MessageErrorEnum.GDMT000.getCode());
        }
    }

    private LogicalFolderEntity findLogicalFolderEntityById(UUID logicalFolderId) {
        return logicalFolderRepository.findById(logicalFolderId)
                .orElseThrow(() -> new InvalidDataException(
                        MessageErrorEnum.GDMT001.getDescription(),
                        MessageErrorEnum.GDMT001.getCode()));
    }

    private LogicalFolderEntity saveLogicalFolder(LogicalFolderEntity logicalFolderEntity) {
        return logicalFolderRepository.save(logicalFolderEntity);
    }

}
