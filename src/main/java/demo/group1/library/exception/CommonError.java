package demo.group1.library.exception;

import org.springframework.http.HttpStatus;

public interface CommonError {
    HttpStatus status();

    String message();
}
