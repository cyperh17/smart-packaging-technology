package smart.packaging.technology.services.resource.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import smart.packaging.technology.services.dto.ErrorResponse;
import smart.packaging.technology.services.dto.FieldErrorInfo;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class RestExceptionHandlerImpl {

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<String> notReadableRequestExceptionHandler(
            org.springframework.http.converter.HttpMessageNotReadableException e, WebRequest request) {
        log.info("RestExceptionHandlerImpl.notReadableRequestExceptionHandler executed");
        return createErrorResponse(e, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, WebRequest request) {
        log.info("RestExceptionHandlerImpl.methodArgumentNotValidExceptionHandler executed");

        ErrorResponse response = new ErrorResponse();
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        List<FieldErrorInfo> errorDetails = new ArrayList<>();

        for (FieldError err : errors) {
            FieldErrorInfo fieldErr = new FieldErrorInfo();

            fieldErr.setField(err.getField());
            fieldErr.setError(err.getDefaultMessage());
            fieldErr.setSuppliedValue(err.getRejectedValue());

            errorDetails.add(fieldErr);
        }

        response.setErrors(errorDetails);
        response.setHttpCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> throwableHandler(Throwable t, WebRequest request) {
        log.info("RestExceptionHandlerImpl.throwableHandler executed");
        return createErrorResponse(t, request, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> createErrorResponse(Throwable e, WebRequest request, HttpStatus status) {
        log.error("Error happened: " + e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), status);
    }
}
