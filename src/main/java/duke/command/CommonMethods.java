package duke.command;

import duke.exception.DukeException;

/**
 * A utility class containing common methods for executing the commands .
 */
public class CommonMethods {

    /**
     * Retrieves the index of a task from the user input command.
     *
     * @param cmd The user input command containing the task number.
     * @return The index of the task specified in the command.
     * @throws DukeException If there is an error while extracting the task index.
     */
    static int getIndex(String cmd) throws DukeException {
        if (cmd.isEmpty() || cmd.equals(" ")) {
            throw new DukeException(
                    "☹ OOPS!!! You need to tell me which task you want to act on.");
        }else if(!cmd.matches(" \\d*")){
            throw new DukeException(
                    "☹ OOPS!!! Please follow the following pattern to act on a task:\n  " +
                            "<your action> <task number>\n" +
                            "You can find the task number by calling 'list'");
        }else {
            return Integer.parseInt(cmd.substring(1));
        }
    }
}
