package com.colegiominayticha.ccplgdmtlogicalfolder.service;

import com.colegiominayticha.ccplgdmtlogicalfolder.model.RestConsumerRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.consumer.LogicalFolderInformation;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.consumer.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.consumer.LogicalFolderResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class LogicalFolderServiceImpl implements ILogicalFolderService {

    @Override
    public LogicalFolderResponseDto createLogicalFolder(
            RestConsumerRequestDto<LogicalFolderRequestDto> restConsumerRequest) {
        log.info("Entered /logical-folders ServiceImpl");
        return LogicalFolderResponseDto
                .builder()
                .id(UUID.randomUUID())
                .parentId(UUID.randomUUID())
                .folderInformation(
                        LogicalFolderInformation
                                .builder()
                                .name("Actas")
                                .createdUser("pedro.chavez")
                                .createdAt(LocalDateTime.now())
                                .build()
                )
                .build();
    }

}
