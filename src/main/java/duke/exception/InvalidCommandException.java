package duke.exception;

public class InvalidCommandException extends Exception {
    protected static String HORIZONTAL_LINE = "    ____________________________________________________________"; //60 underscores.
    public InvalidCommandException(String message) {
        super(message);
    }

    public void printExceptionMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     OOPS!!! " + this.getMessage());
        System.out.println("     Please input valid commands. Currently SeeWhyAre bot supports:");
        System.out.println("     todo      deadline\n     event     list\n"
                + "     mark      unmark\n     delete    bye");
        System.out.println(HORIZONTAL_LINE);
    }
}
