package demo.group1.library.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCodeException implements CommonError {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "bad request"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "not found");

    private final HttpStatus status;
    private final String message;

    private ErrorCodeException(HttpStatus httpStatus, String message) {
        this.status = httpStatus;
        this.message = message;
    }

    @Override
    public HttpStatus status() {
        return null;
    }

    @Override
    public String message() {
        return null;
    }
}
