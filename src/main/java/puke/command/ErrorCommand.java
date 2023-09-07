package puke.command;

import puke.managers.TaskList;

/**
 * A Command class that when executed, prints the error message.
 */
public class ErrorCommand extends Command {
    public ErrorCommand() {
        super(false, false);
    }


    /**
     * Executes the command by printing out the corresponding message.
     *
     * @param tl The task list.
     * @return the message String.
     */
    public String execute(TaskList tl) {
        return ERROR_MESSAGE;
    }

    /**
     * Returns the boolean representing whether another Object is an instance of an ErrorCommand.
     * Used in testing.
     *
     * @param other Another object.
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof ErrorCommand);
    }
}
