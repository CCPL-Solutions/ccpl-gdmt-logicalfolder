package com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageErrorEnum {

    CCPL_GDMT_G000("CCPL_GDMT_G001", "Logical folder with id '%s' does not exist."),
    CCPL_GDMT_G001("CCPL_GDMT_G000", "Logical folder with name '%s' already exist.");

    private final String code;
    private final String description;

    @JsonCreator
    public static MessageErrorEnum fromCode(String code) {
        for (MessageErrorEnum b : MessageErrorEnum.values()) {
            if (b.code.equals(code)) {
                return b;
            }
        }

        throw new IllegalArgumentException("Unexpected valur '" + code + "'");
    }

}
