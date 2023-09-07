package puke.command;

import puke.managers.DataHandler;
import puke.managers.TaskList;

/**
 * A Command class that when executed, clears the task list.
 */
public class ClearCommand extends Command {
    public ClearCommand(String rest) {
        super(false, rest.isEmpty());
    }

    /**
     * Executes the command by returning the corresponding message.
     * If the command is invalid, an error message is printed instead.
     *
     * @param tl The task list.
     * @return The message for clearing all tasks.
     */
    public String execute(TaskList tl) {
        if (!super.isValid) {
            return ERROR_MESSAGE;
        } else {
            try {
                tl.clear();
                DataHandler.clearAll();
                return "Well I certainly hope you had meant to do that because I am not going too ask for your "
                        + "confirmation. As per the aforementioned instructions, I shall now purge all of the tasks "
                        + "that you have previously recorded and designated as requiring attention.";
            } catch (Exception e) {
                tl.clear();
                return "Well I certainly hope you had meant to do that because I am not going too ask for your "
                        + "confirmation. As per the aforementioned instructions, I shall now purge all of the tasks "
                        + "that you have previously recorded and designated as requiring attention.";
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
