package duke.exception;

import duke.util.Parser;

/**
 * Represents an Invalid Command Exception
 *
 * <p>CS2103T AY23/24 Semester 1
 * Individual Project
 * SeeWhyAre Bot
 * 31 Aug 2023
 *
 * @author Freddy Chen You Ren
 */
public class InvalidCommandException extends Exception {
    protected static String HORIZONTAL_LINE = "    ____________________________________________________________"; //60 underscores.
    public InvalidCommandException(String message) {
        super(message);
    }

    /**
     * Prints the exception message, giving us the reason for the error.
     * Includes the list of commands that the current version of SeeWhyAre bot supports.
     */
    public String printExceptionMessage() {
        StringBuilder message = new StringBuilder(String.format("OOPS!!! %s\n", this.getMessage()));
        message.append("Please input valid commands. Currently SeeWhyAre bot supports:\n");
        for (Parser.Command c : Parser.Command.values()) {
            message.append(c + "\n");
        }
        return message.toString();
//        System.out.println("     OOPS!!! " + this.getMessage());
//        System.out.println("     Please input valid commands. Currently SeeWhyAre bot supports:");
//        for (Parser.Command c : Parser.Command.values()) {
//            System.out.println("     " + c);
//        }
    }
}
