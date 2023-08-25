package exceptions;

public class EmptyNumberException extends HachiException {
    public EmptyNumberException(String check) {
        super(String.format("☹ OOPS!!! Please indicate which task you wish to %s", check));
    }
}