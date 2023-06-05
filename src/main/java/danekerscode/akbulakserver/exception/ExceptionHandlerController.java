package danekerscode.akbulakserver.exception;

import danekerscode.akbulakserver.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EntityPropertiesException.class)
    public ResponseEntity<ErrorResponse> returnErrorsToClient(
            EntityPropertiesException e
    ) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
