package sparta.week1.exception.handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sparta.week1.exception.ExceptionResult;

import java.util.List;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CommonControllerAdvice {

    private final MessageSource ms;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ExceptionResult illegalArgumentHandler(MethodArgumentNotValidException exception) {
        log.info("IllegalException cause by Exception {0}", exception);
        int fieldErrorCount = exception.getFieldErrorCount();
        List<FieldError> fieldErrors = exception.getFieldErrors();
        FieldError fieldError = exception.getFieldError();
        String[] codes = exception.getFieldError().getCodes();
        String default_message = ms.getMessage(new DefaultMessageSourceResolvable(codes, "default Message"), null);
        return new ExceptionResult(exception.getMessage(), exception.getClass().toString());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ExceptionResult exceptionHandler(Exception exception) {
        log.info("IllegalException cause by Exception {0}", exception);
        Exception ex = exception;
        return new ExceptionResult("illegal argument Exception", exception.getClass().toString());

    }

}
