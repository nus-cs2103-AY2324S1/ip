package adam.exception;

/**
 * This exception class is for the wrong format of entering dates.
 */
public class DateException extends AdamException {

    @Override
    public String getInfo() {
        return "OOPS!!! you need the date to be in a yyyy-mm-dd format";
    }
}
