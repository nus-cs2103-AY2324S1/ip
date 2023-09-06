package DukeUIClasses;

import DukeExceptions.DukeInvalidTimeException;
import DukeExceptions.DukeException;
import DukeExceptions.DukeEmptyInputException;
import DukeExceptions.DukeEmptyTaskListException;
import DukeExceptions.DukeInvalidCommandException;
import DukeExceptions.DukeInvalidIndexException;

/**
 * Encapsulates a class which will help to print UI messages.
 *
 * @author Tan Kerway
 */
public class DukeErrorUi {

    /**
     * Handles the case where the tasks list is empty but the user enters a delete command.
     *
     * @author Tan Kerway
     */
    public void handleEmptyTasksList() throws DukeException {
        DukeException e = new DukeEmptyTaskListException();
        System.out.println("------------------------------------------------------------------------");
        System.out.println(e.getMessage());
        System.out.println("------------------------------------------------------------------------");
        throw e;
    }

    /**
     * Returns errors associated with the event command and invalid commands.
     *
     * @author Tan Kerway
     * @param taskString the description of the task
     * @param fromStart the index of the /from string
     * @param toStart the index of the /to string
     * @param taskDescriptionLength the length of the taskString
     */
    public void handleEverythingElseError(String taskString, int fromStart, int toStart, int taskDescriptionLength) throws DukeException {
        // handle errors
        if (!taskString.startsWith("event")) {
            handleInvalidCommand();
        }
        if (taskDescriptionLength == 5) {
            handleEmptyCommand("event");
        }
        if (fromStart == -1 && toStart == -1) {
            handleNoDate("from and to");
        }
        if (fromStart == -1 || fromStart + 5 == toStart) {
            handleNoDate("from");
        }
        if (toStart == -1 || toStart + 3 == taskDescriptionLength) {
            handleNoDate("to");
        }
        if (taskString.substring(fromStart + 5, toStart).trim().equals("")) {
            handleNoDate("from");
        }
    }

    /**
     * Returns errors associated with the deadline command.
     *
     * @author Tan Kerway
     * @param taskString the task description
     * @param taskDescriptionLength the length of the task
     * @param indexOfBy the index of the /by string
     */
    public void handleDeadlineErrors(String taskString, int taskDescriptionLength, int indexOfBy) throws DukeException {
        // handle errors
        if (taskDescriptionLength == 8 || (indexOfBy != -1 &&taskString.substring(8, taskString.lastIndexOf("/by") + 1).trim().isEmpty())) {
            handleEmptyCommand("deadline");
        }
        if (!taskString.contains("/by") || indexOfBy + 3 == taskString.length() || taskString.substring(indexOfBy + 3).trim().equals("")) {
            handleNoDate("by");
        }
    }

    /**
     * Returns errors associated with the todo command.
     *
     * @author Tan Kerway
     * @param taskString the input that the user typed in
     * @param taskDescriptionLength the length of the task that the user typed in
     */
    public void handleTodoErrors(String taskString, int taskDescriptionLength) throws DukeException{
        if (taskDescriptionLength == 4 || taskString.substring(4).trim().equals("")) {
            handleEmptyCommand("todo");
        }
    }

    /**
     * Prints error message telling the user that the command cannot be empty
     *
     * @author Tan Kerway
     * @param message the input command that the user typed in
     */
    private void handleEmptyCommand(String message) throws DukeException {
        DukeException res = new DukeEmptyInputException(message);
        System.out.println("------------------------------------------------------------------------");
        System.out.println(res.getMessage());
        System.out.println("------------------------------------------------------------------------");
        throw res;
    }

    /**
     * Prints error message telling the user that the command is invalid.
     *
     * @author Tan Kerway
     */
    private void handleInvalidCommand() throws DukeException {
        DukeException res = new DukeInvalidCommandException();
        System.out.println("------------------------------------------------------------------------");
        System.out.println(res.getMessage());
        System.out.println("------------------------------------------------------------------------");
        throw res;
    }

    /**
     * Prints error message telling the user that the command is invalid.
     * Applicable for the deadline and event command.
     *
     * @author Tan Kerway
     * @param details the String containing the missing info that the user did
     *                not type in
     */
    private static void handleNoDate(String details) throws DukeException {
        DukeException res = new DukeInvalidTimeException(details);
        System.out.println("------------------------------------------------------------------------");
        System.out.println(res.getMessage());
        System.out.println("------------------------------------------------------------------------");
        throw res;
    }

    /**
     * Method that deals with invalid indexes.
     *
     * @author Tan Kerway
     */
    public void handleInvalidIndex() throws DukeException {
        DukeException e = new DukeInvalidIndexException();
        System.out.println("------------------------------------------------------------------------");
        System.out.println(e.getMessage());
        System.out.println("------------------------------------------------------------------------");
        throw e;
    }
}
