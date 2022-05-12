package lk.sliit.ecommercejavaproject.api.exceptionhandler;

import com.fasterxml.jackson.core.JsonParseException;
import lk.sliit.ecommercejavaproject.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

import static lk.sliit.ecommercejavaproject.commonconstant.Commons.NO_RECORDS_FOUND;

@RestControllerAdvice
@Slf4j
public class AppWideExceptionHandler {
//    Logger logger = LoggerFactory.getLogger(AppWideExceptionHandler.class);

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
        String errorMessage = ((exception.getMessage() == null || exception.getMessage().isEmpty())
                ? NO_RECORDS_FOUND : "Record not found: " +
                exception.getMessage());
        log.info(errorMessage);
        return errorMessage;
    }

//    @ResponseStatus(code = HttpStatus.NOT_FOUND)
//    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
//    public String NoMatchingRecordNotFoundException() {
//        return "Record not found.";
//    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class, JsonParseException.class})
    public String BadRequestException(BadRequestException badRequestException) {
        return (badRequestException == null) ? "Invalid data." : "Invalid data: " + badRequestException.getMessage();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeInvalidException.class)
    public String DateTimeInvalidException() {
        return "Provided DateTime is invalid.";
    }

    // DateTimeParseException
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {ZonedDateTimeParseException.class, DateTimeParseException.class})
    public String ZonedDateTimeParseException(Throwable t) {
        log.error("Error occurred when parsing String to ZonedDateTime.", t);
        return "Something went wrong, please contact IT Dept.";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public String globalExceptionHandler(Throwable t) {
        log.error(null, t);
        return "Something went wrong, please contact IT Dept.";
    }
}
