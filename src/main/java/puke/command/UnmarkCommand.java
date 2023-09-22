package puke.command;

import puke.managers.DataHandler;
import puke.managers.PukeException;
import puke.managers.TaskList;

/**
 * A Command class that when executed, marks a task as undone.
 */
public class UnmarkCommand extends Command {
    private static final String UNMARK_MESSAGE = "Very well. I have acknowledged your request to unmark the task of "
            + "specified index as having been completed and will now proceed to set said task of specified index to be "
            + "considered as having not yet been completed.";
    private int index;

    /**
     * Creates a new UnmarkCommand
     * @param rest the rest of the input line.
     * @throws PukeException if an invalid input line is provided
     */
    public UnmarkCommand(String rest) throws PukeException {
        super(false, true);
        try {
            this.index = Integer.parseInt(rest);
        } catch (Exception e) {
            throw new PukeException();
        }
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is in the wrong format or an index is out of bounds, prints an error message instead.
     *
     * @param tl The task list.
     * @return the message String.
     */
    public String execute(TaskList tl) {
        try {
            tl.unmark(this.index);
            DataHandler.writeToDatabase(tl);
            return UNMARK_MESSAGE;
        } catch (Exception e) {
            return ERROR_MESSAGE;
        }
    }

    /**
     * Returns a boolean indicating if the other object has the same toString() and is an instance of UnmarkCommand.
     *
     * @param other Another object
     * @return a boolean.
     */
    @Override
    public boolean equals(Object other) {
        boolean isInstance = other instanceof UnmarkCommand;
        boolean isSameCommand = other.toString().equals(toString());
        return isInstance && isSameCommand;
    }

    /**
     * Returns a String representation of the command
     *
     * @return a String.
     */
    @Override
    public String toString() {
        return "unmark " + this.index;
    }
}
