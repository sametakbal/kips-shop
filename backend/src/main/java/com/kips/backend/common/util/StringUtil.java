package com.kips.backend.common.util;

import com.kips.backend.common.exception.GeneralException;
import org.apache.commons.lang3.StringUtils;

public class StringUtil {
    private StringUtil() {
    }

    public static void fieldCheckNullOrEmpty(String value, String fieldName){
        if (StringUtils.isEmpty(value)) {
            throw new GeneralException(String.format("%s can not be empty!",fieldName));
        }
    }
}
