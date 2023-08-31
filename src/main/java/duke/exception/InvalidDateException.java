package duke.exception;

public class InvalidDateException extends Exception {
    @Override
    public String toString() {
        return "     Yoho! The date provided is invalid. " +
                "Ensure all dates are of the format YYYY-MM-DD. Thanks!";
    }
}
