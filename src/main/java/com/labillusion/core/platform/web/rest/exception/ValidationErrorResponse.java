package com.labillusion.core.platform.web.rest.exception;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg.chen on 14-10-20.
 */
public class ValidationErrorResponse {
    private final List<FieldError> fieldErrors = new ArrayList<FieldError>();

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
