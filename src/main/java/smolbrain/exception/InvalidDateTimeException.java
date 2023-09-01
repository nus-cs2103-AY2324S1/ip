package smolbrain.exception;

public class InvalidDateTimeException extends Exception{

    public InvalidDateTimeException() {
        super();
    }

    @Override
    public String toString() {
        return "Please provide a valid date and time with format of dd//MM/yyyy HHmm.";
    }

}