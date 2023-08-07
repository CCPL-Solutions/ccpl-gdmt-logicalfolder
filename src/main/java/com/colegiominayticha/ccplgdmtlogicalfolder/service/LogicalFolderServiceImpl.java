package com.colegiominayticha.ccplgdmtlogicalfolder.service;

import com.ccplsolutions.common.exception.classification.business.InvalidDataException;
import com.ccplsolutions.common.model.RestRequestDto;
import com.ccplsolutions.security.service.AuthenticationService;
import com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.constant.HeaderConstant;
import com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.constant.MessageErrorEnum;
import com.colegiominayticha.ccplgdmtlogicalfolder.dataprovider.jpa.entity.LogicalFolderEntity;
import com.colegiominayticha.ccplgdmtlogicalfolder.dataprovider.jpa.entity.SpecificMetadataEntity;
import com.colegiominayticha.ccplgdmtlogicalfolder.dataprovider.jpa.repository.ILogicalFolderRepository;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderInformation;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderResponseDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.SpecificMetadataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LogicalFolderServiceImpl implements ILogicalFolderService {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ILogicalFolderRepository logicalFolderRepository;

    @Override
    public LogicalFolderResponseDto createLogicalFolder(
            RestRequestDto<LogicalFolderRequestDto> restRequest) {

        log.info("Entered /logical-folders Service");
        log.debug("RestRequestDto: {}", restRequest);

        LogicalFolderRequestDto requestBody = restRequest.getBody();
        Map<String, Object> requestHeaders = restRequest.getHeaders();

        validateExistingFolder(requestBody);

        LogicalFolderEntity logicalFolderEntityParent = findLogicalFolderById(requestBody.getParentId());

        LogicalFolderEntity logicalFolderEntity = new LogicalFolderEntity();
        logicalFolderEntity.setName(requestBody.getName());
        logicalFolderEntity.setDescription(requestBody.getDescription());
        logicalFolderEntity.setCreatedUser((String) requestHeaders.get(HeaderConstant.X_USER_ID));
        logicalFolderEntity.setCreatedAt(LocalDateTime.now());
        logicalFolderEntity.setParent(logicalFolderEntityParent);
        requestBody.getSpecificMetadata().forEach(specificMetadata -> this.mapInSpecificMetadata(logicalFolderEntity, specificMetadata));

        LogicalFolderEntity logicalFolderEntitySaved = logicalFolderRepository.save(logicalFolderEntity);

        LogicalFolderResponseDto logicalFolderResponseDto = new LogicalFolderResponseDto();
        logicalFolderResponseDto.setId(logicalFolderEntitySaved.getId());
        logicalFolderResponseDto.setParentId(logicalFolderEntitySaved.getParent().getId());
        logicalFolderResponseDto.setSpecificMetadata(logicalFolderEntitySaved.getSpecificMetadata()
                .stream()
                .map(this::mapOutSpecificMetadata)
                .collect(Collectors.toList()));

        LogicalFolderInformation logicalFolderInformation = new LogicalFolderInformation();
        logicalFolderInformation.setName(logicalFolderEntitySaved.getName());
        logicalFolderInformation.setDescription(logicalFolderEntitySaved.getDescription());
        logicalFolderInformation.setCreatedUser(logicalFolderEntitySaved.getCreatedUser());
        logicalFolderInformation.setCreatedAt(logicalFolderEntitySaved.getCreatedAt());

        logicalFolderResponseDto.setFolderInformation(logicalFolderInformation);

        return logicalFolderResponseDto;
    }

    private void mapInSpecificMetadata(LogicalFolderEntity logicalFolderEntity, SpecificMetadataDto specificMetadataDto) {
        SpecificMetadataEntity specificMetadataEntity = new SpecificMetadataEntity();
        specificMetadataEntity.setName(specificMetadataDto.getName());
        specificMetadataEntity.setValue(specificMetadataDto.getValue());
    }

    private SpecificMetadataDto mapOutSpecificMetadata(SpecificMetadataEntity specificMetadata) {
        return null;
    }

    private void validateExistingFolder(LogicalFolderRequestDto requestBody) {
        Optional<LogicalFolderEntity> logicalFolder = logicalFolderRepository.findByName(requestBody.getName());
        if (logicalFolder.isPresent()) {
            throw new InvalidDataException(
                    MessageErrorEnum.CCPL_GDMT_G001.getDescription(),
                    MessageErrorEnum.CCPL_GDMT_G001.getCode());
        }
    }

    private LogicalFolderEntity findLogicalFolderById(UUID logicalFolderId) {
        return logicalFolderRepository.findById(logicalFolderId)
                .orElseThrow(() -> new InvalidDataException(
                        MessageErrorEnum.CCPL_GDMT_G000.getDescription(),
                        MessageErrorEnum.CCPL_GDMT_G000.getCode()));
    }

}
