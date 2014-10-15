package com.labillusion.core.platform.web.rest.exception;

import com.labillusion.core.platform.runtime.RuntimeEnvironment;
import com.labillusion.core.platform.runtime.RuntimeSettings;
import org.springframework.stereotype.Component;

/**
 * Created by greg.chen on 14-10-14.
 */
@Component
public class ErrorResponseBuilder {

    public ErrorResponse createErrorResponse(Throwable e) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setExceptionClass(e.getClass().getName());
       // error.setRequestId(requestContext.getRequestId());
        if (RuntimeEnvironment.DEV.equals(RuntimeSettings.get().getEnvironment())) {
            error.setExceptionTrace(ExceptionUtils.stackTrace(e));
        }

        return error;
    }
}
