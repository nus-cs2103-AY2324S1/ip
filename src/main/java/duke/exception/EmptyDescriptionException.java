package duke.exception;

public class EmptyDescriptionException extends Exception {
    protected static String HORIZONTAL_LINE = "    ____________________________________________________________"; //60 underscores.

    public EmptyDescriptionException(String message) {
        super(message);
    }

    public void printExceptionMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     OOPS!!! " + this.getMessage());
        System.out.println(HORIZONTAL_LINE);
    }
}
