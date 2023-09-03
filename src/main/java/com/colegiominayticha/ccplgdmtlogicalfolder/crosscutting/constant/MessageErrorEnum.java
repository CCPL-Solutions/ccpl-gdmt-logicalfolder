package com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageErrorEnum {

    GDMT000("GDMT000", "Logical folder with name '%s' already exist."),
    GDMT001("GDMT001", "Logical folder with id '%s' does not exist."),
    GDMT002("GDMT002", "The logical folder cannot be deleted because it has child components.");

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
