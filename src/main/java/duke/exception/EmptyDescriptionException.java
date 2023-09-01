package duke.exception;

/**
 * Represents an Empty Description Exception
 *
 * <p>CS2103T AY23/24 Semester 1
 * Individual Project
 * SeeWhyAre Bot
 * 31 Aug 2023
 *
 * @author Freddy Chen You Ren
 */
public class EmptyDescriptionException extends Exception {
    protected static String HORIZONTAL_LINE = "    ____________________________________________________________"; //60 underscores.

    public EmptyDescriptionException(String message) {
        super(message);
    }

    /**
     * Prints the exception message, giving us the reason for the error.
     */
    public void printExceptionMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     OOPS!!! " + this.getMessage());
        System.out.println(HORIZONTAL_LINE);
    }
}
