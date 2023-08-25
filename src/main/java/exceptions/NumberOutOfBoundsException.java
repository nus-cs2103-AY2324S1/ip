package exceptions;

public class NumberOutOfBoundsException extends HachiException {
    public NumberOutOfBoundsException(int num) {
        super(String.format("☹ OOPS!!! Task number is out of bounds. You only have %d tasks currently.", num));
    }
}