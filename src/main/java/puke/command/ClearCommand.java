package puke.command;

import puke.managers.DataHandler;
import puke.managers.TaskList;
import puke.managers.Ui;

/**
 * A Command class that when executed, clears the task list.
 */
public class ClearCommand extends Command {
    public ClearCommand(String rest) {
        super(false, rest.isEmpty());
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is invalid, an error message is printed instead.
     *
     * @param tl The task list.
     * @param ui The UI.
     */
    public void execute(TaskList tl, Ui ui) {
        if (!super.isValid) {
            System.out.println(Ui.errorMessage());
            System.out.println(Ui.separator());
        } else {
            try {
                tl.clear();
                DataHandler.clearAll();
                System.out.println(ui.clear());
                System.out.println(Ui.separator());
            } catch (Exception e) {
                tl.clear();
                System.out.println(ui.clear());
                System.out.println(Ui.separator());
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
