package duke.exceptions;

public class DukeException extends Exception{

    private String errDescription;

    public DukeException(String errDescription) {
        this.errDescription = errDescription;
    }

    public String toString() {
        return "OOPS! " + errDescription;
    }

    public String getMessage() {
        return "OOPS! " + errDescription;
    }
    
}
