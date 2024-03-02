package demo.group1.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ApiException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;
    private String spec;

    public ApiException(CommonError commonError, String spec) {
        this.httpStatus = commonError.status();
        this.message = String.format("%s %s", spec, commonError.message());
    }
}
