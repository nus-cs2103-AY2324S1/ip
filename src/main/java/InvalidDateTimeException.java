public class InvalidDateTimeException extends Exception{

    public InvalidDateTimeException() {
        super();
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! Please provide a valid date and time.";
    }

}