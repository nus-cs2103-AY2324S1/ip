package DukeExceptionHandlers;

import DukeExceptions.DukeException;
import DukeExceptions.DukeInvalidTimeException;

/**
 * Encapsulates a class which is designed to handle exceptions.
 *
 * @author Tan Kerway
 */
public class DukeExceptionHandlers {

    /**
     * Prints error message telling the user that the command is invalid.
     * Applicable for the deadline and event command.
     *
     * @author Tan Kerway
     * @param details the String containing the missing info that the user did
     *                not type in
     */
    public static void handleNoDate(String details) {
        DukeException res = new DukeInvalidTimeException(details);
        System.out.println("------------------------------------------------------------------------");
        System.out.println(res.getMessage());
        System.out.println("------------------------------------------------------------------------");
    }
}
