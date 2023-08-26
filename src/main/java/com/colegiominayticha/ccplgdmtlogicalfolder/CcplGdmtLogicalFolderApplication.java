package com.colegiominayticha.ccplgdmtlogicalfolder;

import com.ccplsolutions.common.config.CCPLExceptionConfiguration;
import com.ccplsolutions.security.config.CCPLSecurityBeanConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
@Import({
        CCPLExceptionConfiguration.class,
        CCPLSecurityBeanConfiguration.class
})
public class CcplGdmtLogicalFolderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcplGdmtLogicalFolderApplication.class, args);
    }

}