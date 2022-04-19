package sparta.week1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResult {

    private String message;
    private String exception;
}
