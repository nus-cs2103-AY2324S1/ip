package dan.exceptions;

import java.util.InputMismatchException;

public class DanException extends InputMismatchException {

    public DanException(String s) {
        super(s);
    }
}
