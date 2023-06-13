package com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.util;

import com.colegiominayticha.ccplgdmtlogicalfolder.crosscutting.constant.HeaderConstant;

import java.util.HashMap;
import java.util.Map;

public class Util {
    public static Map<String, Object> getHeaders(String xUserId) {
        Map<String, Object> headers =  new HashMap<>();
        headers.put(HeaderConstant.X_USER_ID, xUserId);
        return headers;
    }
}
