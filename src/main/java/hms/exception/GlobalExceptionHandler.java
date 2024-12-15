package hms.exception;

import hms.util.ResponseTo;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseTo> invalidMethodArgument(MethodArgumentNotValidException e){
        List<String> errors = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        log.error("Method argument not valid "+ errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseTo.builder().data(errors).statusCode(HttpStatus.BAD_REQUEST.value()).build());
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ResponseTo> invalidDataException(InvalidDataException e) {
        log.error("Invalid data provided : "+ e);
        return new ResponseEntity<>(ResponseTo.builder().data(e.getMessage()).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseTo> notFoundException(NotFoundException e) {
        log.error("Provided data not found : "+ e);
        return new ResponseEntity<>(ResponseTo.builder().data(e.getMessage()).statusCode(HttpStatus.NOT_FOUND.value()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseTo> exception(Exception e) {
        log.error("Internal server error: "+ e);
        return new ResponseEntity<>(ResponseTo.builder().data(e.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
