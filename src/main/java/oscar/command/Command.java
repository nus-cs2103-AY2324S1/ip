package oscar.command;

import java.time.format.DateTimeFormatter;

import oscar.essential.Storage;
import oscar.essential.TaskList;
import oscar.exception.OscarException;

/**
 * Abstract class for various Oscar commands.
 */
public abstract class Command {
    static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final boolean isExit;

    /**
     * Constructor for command subclasses.
     *
     * @param isExit Whether command is an exit command.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Validates the provided task number.
     *
     * @param details Task number as a string.
     * @param tasks ArrayList of tasks.
     * @return Integer task number.
     * @throws OscarException Failure to validate task number.
     */
    public int validateInt(String details, TaskList tasks) throws OscarException {
        if (details.isEmpty()) {
            throw new OscarException("Sorry! The task number cannot be empty.\n");
        }
        int index;
        try {
            index = Integer.parseInt(details) - 1;
            if (index < 0) {
                throw new OscarException("Sorry! Task numbers must be natural numbers.\n");
            } else if (index >= tasks.getSize()) {
                throw new OscarException("Sorry! Task number is too large.\n");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new OscarException("Sorry! Please enter the number of the task to be marked as done.\n");
        }
    }

    /**
     * Carries out the intended function of a command.
     *
     * @param tasks ArrayList of tasks.
     * @param storage File loading and saving handler.
     * @throws OscarException Failure to execute command.
     */
    public abstract void execute(TaskList tasks, Storage storage) throws OscarException;

    /**
     * Checks if command is an exit command.
     *
     * @return Whether command is an exit command.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
