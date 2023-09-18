package puke.command;

import puke.managers.DataHandler;
import puke.managers.PukeException;
import puke.managers.TaskList;

/**
 * A Command class that when executed, clears the task list.
 */
public class ClearCommand extends Command {
    private static final String CLEAR_MESSAGE = "Well I certainly hope you had meant to do that because I am not "
            + "going too ask for your confirmation. As per the aforementioned instructions, I shall now purge all of "
            + "the tasks that you have previously recorded and designated as requiring attention.";

    /**
     * Creates a new ClearCommand that when executed, clears the task list.
     * @param rest the rest of the input line
     * @throws PukeException if the format of the input is incorrect
     */
    public ClearCommand(String rest) throws PukeException {
        super(false, rest.isEmpty());
        if (!rest.isEmpty()) {
            throw new PukeException();
        }
    }

    /**
     * Executes the command by returning the corresponding message.
     * If the command is invalid, an error message is printed instead.
     *
     * @param tl The task list.
     * @return The message for clearing all tasks.
     */
    public String execute(TaskList tl) {
        if (!isValid) {
            return ERROR_MESSAGE;
        } else {
            try {
                tl.clear();
                DataHandler.clearAll();
                return CLEAR_MESSAGE;
            } catch (Exception e) {
                tl.clear();
                return CLEAR_MESSAGE;
            }
        }
    }

    /**
     * Returns a boolean indicating if the other object is an instance of ClearCommand.
     * Used in testing.
     *
     * @param other Another object.
     * @return a boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof ClearCommand);
    }
}
