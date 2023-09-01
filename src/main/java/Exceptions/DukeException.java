package Exceptions;

public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    public String printError() {
        return getMessage();
    }


}
