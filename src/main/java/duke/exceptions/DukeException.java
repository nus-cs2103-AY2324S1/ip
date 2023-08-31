package duke.exceptions;

public class DukeException extends Exception{

    private String errDescription;

    public DukeException(String errDescription) {
        this.errDescription = errDescription;
    }

    public String toString() {
        return "OOPS! " + this.errDescription;
    }

    public String getMessage() {
        return "OOPS! " + this.errDescription;
    }
    
}
