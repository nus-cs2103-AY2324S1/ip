package Utils;

import java.lang.RuntimeException;

public class DukeException extends RuntimeException {

    private String errorMessage;

    protected DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "OOPS!!! " + errorMessage;
    }
}
