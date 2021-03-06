package nl.rabobank.powerofattorney.web.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import nl.rabobank.powerofattorney.domain.exceptions.InactiveEntityException;
import nl.rabobank.powerofattorney.domain.exceptions.NotFoundException;
import nl.rabobank.powerofattorney.domain.exceptions.UnauthorizedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFound(NotFoundException ex) {
        return makeResponse(HttpStatus.NOT_FOUND, ex);
    }

    // Normally IllegalArgumentException but @NonNull (lombok) throws NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> nullInput(NullPointerException ex) {
        return makeResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> illegalArgument(IllegalArgumentException ex) {
        return makeResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionResponse> unauthorized(UnauthorizedException ex) {
        return makeResponse(HttpStatus.UNAUTHORIZED, ex);
    }

    @ExceptionHandler(InactiveEntityException.class)
    public ResponseEntity<ExceptionResponse> unauthorized(InactiveEntityException ex) {
        return makeResponse(HttpStatus.BAD_REQUEST, ex);
    }

    private ResponseEntity<ExceptionResponse> makeResponse(HttpStatus errorCode, Exception ex) {
        ExceptionResponse response = new ExceptionResponse(errorCode, ex.getMessage());
        return new ResponseEntity<>(response, errorCode);
    }
}
