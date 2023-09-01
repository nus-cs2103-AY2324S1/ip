package Exceptions;

public class InvalidDeadlineException extends LemonException {
    public InvalidDeadlineException(String message) {
        super(":( OPPS!!! Please add a deadline with /by & date in format yyyy-mm-dd!");
    }
}
