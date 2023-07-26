package com.colegiominayticha.ccplgdmtlogicalfolder.service;

import com.ccplsolutions.common.model.RestRequestDto;
import com.ccplsolutions.security.service.AuthenticationService;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderInformation;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderRequestDto;
import com.colegiominayticha.ccplgdmtlogicalfolder.model.LogicalFolderResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class LogicalFolderServiceImpl implements ILogicalFolderService {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public LogicalFolderResponseDto createLogicalFolder(
            RestRequestDto<LogicalFolderRequestDto> restRequest) {

        log.info("Entered /logical-folders Service");
        log.debug("RestRequestDto: {}", restRequest);

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
