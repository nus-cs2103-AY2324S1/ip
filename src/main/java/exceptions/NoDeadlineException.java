package exceptions;

public class NoDeadlineException extends HachiException {
    public NoDeadlineException() {
        super("☹ OOPS!!! Missing deadline, please input using this format:" +
                " \"deadline <description> /by <deadline>\"");
    }
}