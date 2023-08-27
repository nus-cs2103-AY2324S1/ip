package Exception;

public class InvalidDateException extends Exception {
    @Override
    public String toString() {
        return "  ☹ OOPS!!! Please fill in a valid date!";
    }
}
