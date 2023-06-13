package com.colegiominayticha.ccplgdmtlogicalfolder.model;

import lombok.*;

import java.util.Map;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestConsumerRequestDto<T> {

    T body;
    private Map<String, Object> headers;
    private Map<String, Object> pathParams;
    private Map<String, Object> queryParams;

}
