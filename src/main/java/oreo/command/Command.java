package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.exception.IllegalDateTimeException;
import oreo.task.Task;
import oreo.task.TaskList;

/**
 * Implements abstract command class, parent class of specific commands
 *
 * @author Daniel Loh
 * @version 03/09/2023
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks Tasklist of the chatbot.
     * @return Response from executing command on tasks
     */
    public abstract String execute(TaskList tasks);

    /**
     * Executes the command while in edit mode.
     *
     * @param tasks task list.
     * @param index index of relevant task in edit mode.
     * @param oldTask old task of edit mode.
     * @return Response from executing command on tasks.
     * @throws IllegalDateTimeException Invalid date time formats.
     */
    public abstract String executeEditMode(TaskList tasks, int index, Task oldTask)
            throws IllegalDateTimeException;

    /**
     * Checks if command is bye.
     *
     * @return true if command is ByeCommand.
     */
    public boolean isExit() {
        return this instanceof ByeCommand;
    }

    /**
     * Checks if command is edit.
     *
     * @return true if command is EditCommand.
     */
    public boolean isEdit() {
        return this instanceof EditCommand;
    }

    /**
     * Checks if command is ExitMode.
     *
     * @return true if command is ExitModeCommand.
     */
    public boolean isExitMode() {
        return this instanceof ExitModeCommand;
    }

    /**
     * Utility method to check if str is an integer input for commands that
     * take in index input.
     *
     * @param str input
     * @return true if integer, false if not
     */
    public boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
