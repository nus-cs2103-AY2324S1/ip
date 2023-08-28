package exception;

public class InvalidTimeException extends Exception{
    @Override
    public String toString() {
        return "The given date is invalid. Returning to homepage...";
    }
}
