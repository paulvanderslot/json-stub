package nl.rabobank.powerofattorney.web.exceptionhandling;

import org.springframework.http.HttpStatus;

import lombok.Value;

@Value
public class ExceptionResponse {
    HttpStatus errorCode;
    String errorMessage;
}
