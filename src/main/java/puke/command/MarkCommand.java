package puke.command;

import puke.managers.DataHandler;
import puke.managers.PukeException;
import puke.managers.TaskList;

/**
 * A Command class that when executed, prints the message to mark a task as complete.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a new MarkCommand
     * @param rest the rest of the input line.
     * @throws PukeException if an invalid input line is provided
     */
    public MarkCommand(String rest) throws PukeException {
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
            tl.mark(this.index);
            DataHandler.writeToDatabase(tl);
            return generateMessage();
        } catch (Exception PukeException) {
            return ERROR_MESSAGE;
        }
    }
    private String generateMessage() {
        return "I have been made aware of your desire to indicate that the task numbered "
                + index
                + " has been since been achieved as of the time at which you have stipulated as so.";
    }

    /**
     * Returns a boolean indicating if the other object has the same toString() and is an instance of MarkCommand.
     *
     * @param other Another object
     * @return a boolean.
     */
    @Override
    public boolean equals(Object other) {
        boolean isInstance = other instanceof MarkCommand;
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
        return "mark " + this.index;
    }
}
