package Utility;

public class DukeException extends Exception{

    private String errDescription;

    public DukeException(String errDescription) {
        this.errDescription = errDescription;
    }

    public String toString() {
        return this.errDescription;
    }
    
}
