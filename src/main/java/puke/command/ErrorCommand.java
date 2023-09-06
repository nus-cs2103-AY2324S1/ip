package puke.command;

import puke.managers.TaskList;
import puke.managers.Ui;

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
     * @param ui The UI.
     */
    public void execute(TaskList tl, Ui ui) {
        System.out.println(Ui.errorMessage());
        System.out.println(Ui.separator());
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
