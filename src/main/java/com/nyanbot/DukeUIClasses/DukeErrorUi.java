package com.nyanbot.DukeUIClasses;

import com.nyanbot.DukeExceptions.DukeInvalidTimeException;
import com.nyanbot.DukeExceptions.DukeException;
import com.nyanbot.DukeExceptions.DukeEmptyInputException;
import com.nyanbot.DukeExceptions.DukeEmptyTaskListException;
import com.nyanbot.DukeExceptions.DukeInvalidCommandException;
import com.nyanbot.DukeExceptions.DukeInvalidIndexException;
import com.nyanbot.DukeTasks.InvalidTask;
import com.nyanbot.DukeTasks.Task;

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
     * @return String that represents the error
     */
    public String handleEmptyTasksList() {
        return new DukeEmptyTaskListException().toString();
    }

    /**
     * Returns errors associated with the event command and invalid commands.
     *
     * @author Tan Kerway
     * @param taskString the description of the task
     * @param fromStart the index of the /from string
     * @param toStart the index of the /to string
     * @param taskDescriptionLength the length of the taskString
     * @return an InvalidTask instance if the command is invalid,
     *         null otherwise.
     */
    public Task handleEverythingElseError(String taskString, int fromStart, int toStart, int taskDescriptionLength) {
        // handle errors
        if (!taskString.startsWith("event")) {
            return new InvalidTask(handleInvalidCommand());
        }
        if (taskDescriptionLength == 5) {
            return new InvalidTask(handleEmptyCommand("event"));
        }
        if (fromStart == -1 && toStart == -1) {
            return new InvalidTask(handleNoDate("from and to"));
        }
        if (fromStart == -1 || fromStart + 5 == toStart) {
            return new InvalidTask(handleNoDate("from"));
        }
        if (toStart == -1 || toStart + 3 == taskDescriptionLength) {
            return new InvalidTask(handleNoDate("to"));
        }
        if (taskString.substring(fromStart + 5, toStart).trim().equals("")) {
            return new InvalidTask(handleNoDate("from"));
        }
        return null;
    }

    /**
     * Returns errors associated with the deadline command.
     *
     * @author Tan Kerway
     * @param taskString the task description
     * @param taskDescriptionLength the length of the task
     * @param indexOfBy the index of the /by string
     * @return an InvalidTask if the task is invalid, null otherwise
     */
    public Task handleDeadlineErrors(String taskString, int taskDescriptionLength, int indexOfBy) throws DukeException {
        // handle errors
        if (taskDescriptionLength == 8 || (indexOfBy != -1 &&taskString.substring(8, taskString.lastIndexOf("/by") + 1).trim().isEmpty())) {
            return new InvalidTask(handleEmptyCommand("deadline"));
        }
        if (!taskString.contains("/by") || indexOfBy + 3 == taskString.length() || taskString.substring(indexOfBy + 3).trim().equals("")) {
            return new InvalidTask(handleNoDate("by"));
        }
        return null;
    }

    /**
     * Returns errors associated with the todo command.
     *
     * @author Tan Kerway
     * @param taskString the input that the user typed in
     * @param taskDescriptionLength the length of the task that the user typed in
     * @return an invalid task if the command is invalid, null otherwise
     */
    public Task handleTodoErrors(String taskString, int taskDescriptionLength) throws DukeException{
        if (taskDescriptionLength == 4 || taskString.substring(4).trim().equals("")) {
            return new InvalidTask(handleEmptyCommand("todo"));
        }
        return null;
    }

    /**
     * Prints error message telling the user that the command cannot be empty
     *
     * @author Tan Kerway
     * @param message the input command that the user typed in
     * @return the String associated with an empty error
     */
    private String handleEmptyCommand(String message) {
        return new DukeEmptyInputException(message).getMessage();
    }

    /**
     * Prints error message telling the user that the command is invalid.
     *
     * @author Tan Kerway
     * @return the String representing the error message
     */
    private String handleInvalidCommand() {
        return new DukeInvalidCommandException().getMessage();
    }

    /**
     * Prints error message telling the user that the command is invalid.
     * Applicable for the deadline and event command.
     *
     * @author Tan Kerway
     * @param details the String containing the missing info that the user did
     *                not type in
     * @return the String associated with a no date error
     */
    private static String handleNoDate(String details) {
        return new DukeInvalidTimeException(details).getMessage();
    }

    /**
     * Method that deals with invalid indexes.
     *
     * @author Tan Kerway
     * @return the String the represents the error
     */
    public String handleInvalidIndex() {
        return new DukeInvalidIndexException().toString();
    }
}
