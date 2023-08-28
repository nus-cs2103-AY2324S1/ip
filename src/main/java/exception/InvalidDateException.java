package exception;

public class InvalidDateException extends Exception {
    @Override
    public String toString() {
        return "The given date is invalid. Returning to homepage...";
    }
}
