package com.labillusion.core.platform.web.rest;

import com.labillusion.core.platform.exception.ResourceNotFoundException;
import com.labillusion.core.platform.exception.SessionTimeOutException;
import com.labillusion.core.platform.exception.UserAuthorizationException;
import com.labillusion.core.platform.web.rest.exception.ErrorResponse;
import com.labillusion.core.platform.web.rest.exception.ErrorResponseBuilder;
import com.labillusion.core.platform.web.rest.exception.FieldError;
import com.labillusion.core.platform.web.rest.exception.ValidationErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

/**
 * Created by greg.chen on 14-10-13.
 */
@RestController
public class RESTController {

    @Autowired
    protected ErrorResponseBuilder errorResponseBuilder;

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse error(Throwable e) {
        return errorResponseBuilder.createErrorResponse(e);
    }

    @ExceptionHandler(value=ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse resourceNotFound(ResourceNotFoundException ex){
        return errorResponseBuilder.createErrorResponse(ex);
    }

    @ExceptionHandler(value=UserAuthorizationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse unauthorized(UserAuthorizationException ex){
        return errorResponseBuilder.createErrorResponse(ex);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse validationError(MethodArgumentNotValidException e) {

        return createValidationResponse(e.getBindingResult());
    }

    private ValidationErrorResponse createValidationResponse(BindingResult bindingResult) {
        Locale locale = LocaleContextHolder.getLocale();
        ValidationErrorResponse response = new ValidationErrorResponse();
        List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            FieldError error = new FieldError();
            error.setField(fieldError.getField());
            error.setMessage(fieldError.toString());
            response.getFieldErrors().add(error);
        }
        return response;
    }

    @ExceptionHandler(value=SessionTimeOutException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse sessionTimeOut(SessionTimeOutException ex){
        return errorResponseBuilder.createErrorResponse(ex);
    }

}
