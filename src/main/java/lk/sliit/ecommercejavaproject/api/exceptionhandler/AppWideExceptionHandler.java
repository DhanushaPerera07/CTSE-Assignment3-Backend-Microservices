package lk.sliit.ecommercejavaproject.api.exceptionhandler;

import lk.sliit.ecommercejavaproject.exception.BadRequestException;
import lk.sliit.ecommercejavaproject.exception.DateTimeInvalidException;
import lk.sliit.ecommercejavaproject.exception.IdFormatException;
import lk.sliit.ecommercejavaproject.exception.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static lk.sliit.ecommercejavaproject.commonconstant.Commons.NO_RECORDS_FOUND;

@RestControllerAdvice
public class AppWideExceptionHandler {
    Logger logger = LoggerFactory.getLogger(AppWideExceptionHandler.class);

    /**
     * Since `reason` is returned with the method,
     * HTTP response will NOT contain HTML response body (it just contains the `reason`).
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IdFormatException.class})
    public String handleIdNotValid() {
        return "ID is not valid.";
    }


    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {RecordNotFoundException.class})
    public String recordNotFoundException(RuntimeException exception) {
        logger.info(((exception.getMessage() == null || exception.getMessage().isEmpty())
                ? NO_RECORDS_FOUND : "Record not found: " +
                exception.getMessage()));
        return NO_RECORDS_FOUND;
    }

//    @ResponseStatus(code = HttpStatus.NOT_FOUND)
//    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
//    public String NoMatchingRecordNotFoundException() {
//        return "Record not found.";
//    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public String BadRequestException(BadRequestException badRequestException) {
        return (badRequestException == null) ? "Invalid data." : "Invalid data: " + badRequestException.getMessage();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeInvalidException.class)
    public String DateTimeInvalidException() {
        return "Provided DateTime is invalid.";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public String globalExceptionHandler(Throwable t) {
        logger.error(null, t);
        return "Something went wrong, please contact IT Dept";
    }
}
