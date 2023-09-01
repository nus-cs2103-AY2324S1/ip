package smolbrain.exception;

public class InvalidRangeException extends Exception{

    public InvalidRangeException() {
        super();
    }

    @Override
    public String toString() {
        return "Please provide a valid number within the range.";
    }

}