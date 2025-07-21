package org.ebuilt.ebuiltcloudcommon.exception.exceptionInfo;

import feign.FeignException;

/**
 * Copyright (C), 2025-01-25
 * FileName: CustomFeignException
 * Author:   33879
 * Date:     2025/1/25 17:43
 * Description: 自定义Feign Exception
 */


public class CustomFeignException extends FeignException {
    public CustomFeignException(int status, String message, Throwable cause) {
        super(status, message, cause);
    }
}
