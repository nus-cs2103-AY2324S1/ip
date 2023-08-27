package exceptions;

public class DateFormatWrongException extends HachiException {
    public DateFormatWrongException(String date) {
        super(String.format("Your date is in the wrong format (%s). " +
                "Please enter the date in this format: yyyy-mm-dd", date));
    }
}