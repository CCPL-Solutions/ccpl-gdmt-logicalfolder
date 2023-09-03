package com.colegiominayticha.ccplgdmtlogicalfolder.service.mapper;

import com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.constant.HeaderConstant;
import com.colegiominayticha.ccplgdmtlogicalfolder.dataprovider.jpa.entity.LogicalFolderEntity;
import com.colegiominayticha.ccplgdmtlogicalfolder.dataprovider.jpa.entity.SpecificMetadataEntity;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderInformation;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderResponseDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.SpecificMetadataDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class LogicalFolderMapperImpl implements ILogicalFolderMapper {

    @Override
    public LogicalFolderEntity mapInCreateLogicalFolder(LogicalFolderRequestDto requestBody,
                                                        Map<String, Object> requestHeaders,
                                                        LogicalFolderEntity logicalFolderEntityParent) {

        LogicalFolderEntity logicalFolderEntity = new LogicalFolderEntity();
        logicalFolderEntity.setName(requestBody.getName());
        logicalFolderEntity.setDescription(requestBody.getDescription());
        logicalFolderEntity.setCreatedUser((String) requestHeaders.get(HeaderConstant.X_USER_ID));
        logicalFolderEntity.setCreatedAt(LocalDateTime.now());
        logicalFolderEntity.setParent(logicalFolderEntityParent);
        requestBody.getSpecificMetadata().forEach(specificMetadata -> this.mapInSpecificMetadata(specificMetadata, logicalFolderEntity));
        return logicalFolderEntity;
    }

    @Override
    public LogicalFolderResponseDto mapOutCreateLogicalFolder(LogicalFolderEntity logicalFolderEntitySaved) {
        LogicalFolderResponseDto logicalFolderResponseDto = new LogicalFolderResponseDto();
        logicalFolderResponseDto.setId(logicalFolderEntitySaved.getId());
        logicalFolderResponseDto.setParentId(Objects.nonNull(logicalFolderEntitySaved.getParent()) ? logicalFolderEntitySaved.getParent().getId() : null);
        logicalFolderResponseDto.setSpecificMetadata(logicalFolderEntitySaved.getSpecificMetadata()
                .stream()
                .map(this::mapOutSpecificMetadata)
                .collect(Collectors.toList()));
        logicalFolderResponseDto.setFolderInformation(mapOutLogicalFolderInformation(logicalFolderEntitySaved));
        return logicalFolderResponseDto;
    }

    private void mapInSpecificMetadata(SpecificMetadataDto specificMetadataDto, LogicalFolderEntity logicalFolderEntity) {
        SpecificMetadataEntity specificMetadataEntity = new SpecificMetadataEntity();
        specificMetadataEntity.setName(specificMetadataDto.getName());
        specificMetadataEntity.setValue(specificMetadataDto.getValue());
        logicalFolderEntity.addSpecificMetadata(specificMetadataEntity);
    }

    private SpecificMetadataDto mapOutSpecificMetadata(SpecificMetadataEntity specificMetadata) {
        SpecificMetadataDto specificMetadataDto = new SpecificMetadataDto();
        specificMetadataDto.setId(specificMetadata.getId());
        specificMetadataDto.setName(specificMetadata.getName());
        specificMetadataDto.setValue(specificMetadata.getValue());
        return specificMetadataDto;
    }

    private LogicalFolderInformation mapOutLogicalFolderInformation(LogicalFolderEntity logicalFolderEntity) {
        LogicalFolderInformation logicalFolderInformation = new LogicalFolderInformation();
        logicalFolderInformation.setName(logicalFolderEntity.getName());
        logicalFolderInformation.setDescription(logicalFolderEntity.getDescription());
        logicalFolderInformation.setCreatedUser(logicalFolderEntity.getCreatedUser());
        logicalFolderInformation.setCreatedAt(logicalFolderEntity.getCreatedAt());
        logicalFolderInformation.setUpdatedAt(logicalFolderEntity.getUpdatedAt());
        logicalFolderInformation.setUpdatedUser(logicalFolderEntity.getUpdatedUser());
        return logicalFolderInformation;
    }

}
