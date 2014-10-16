package com.labillusion.core.platform.web.rest.exception;

import com.labillusion.core.platform.runtime.RuntimeEnvironment;
import com.labillusion.core.platform.runtime.RuntimeSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by greg.chen on 14-10-14.
 */
@Component
public class ErrorResponseBuilder {

    private static final Logger logger = LoggerFactory.getLogger(ErrorResponseBuilder.class);

    @Autowired
    private RuntimeSettings runtimeSettings;

    public ErrorResponse createErrorResponse(Throwable e) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setExceptionClass(e.getClass().getName());
       // error.setRequestId(requestContext.getRequestId());
        String errorMessage = ExceptionUtils.stackTrace(e);
        logger.error(errorMessage);
        if (RuntimeEnvironment.DEV.equals(runtimeSettings.getEnvironment())) {
            error.setExceptionTrace(errorMessage);
        }

        return error;
    }
}
