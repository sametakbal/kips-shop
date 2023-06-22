package com.kips.backend.common.util;

import com.kips.backend.common.exception.GeneralException;
import org.apache.commons.lang3.StringUtils;

public class ValidationUtil {
    
    private static final String CAN_NOT_BE_EMPTY = "%s can not be empty!";
    private ValidationUtil() {
    }
    public static void fieldCheckNullOrEmpty(String value, String fieldName){
        if (StringUtils.isEmpty(value)) {
            throw new GeneralException(String.format(CAN_NOT_BE_EMPTY,fieldName));
        }
    }
    public static void fieldCheckNullOrEmpty(Integer value, String fieldName){
        if (value == null) {
            throw new GeneralException(String.format(CAN_NOT_BE_EMPTY,fieldName));
        }
    }
    public static void fieldCheckNullOrEmpty(Float value, String fieldName){
        if (value == null) {
            throw new GeneralException(String.format(CAN_NOT_BE_EMPTY,fieldName));
        }
    }
}
