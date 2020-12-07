package api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class deals with Exceptions that might occur in api package
 */
@RestControllerAdvice
public class SalesStatisticsExceptionHandler {

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNumberFormatException(NumberFormatException exception){
        //Can be written to log file.
    }
}
