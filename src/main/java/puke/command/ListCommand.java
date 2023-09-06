package puke.command;

import puke.managers.TaskList;
import puke.managers.Ui;

/**
 * A Command class that when executed, prints the string representation of the task list.
 */
public class ListCommand extends Command {

    public ListCommand(String rest) {
        super(false, rest.isEmpty());
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is in the wrong format.
     *
     * @param tl The task list.
     * @param ui The UI.
     */
    public void execute(TaskList tl, Ui ui) {
        if (!super.isValid) {
            System.out.println(Ui.errorMessage());
            System.out.println(Ui.separator());
        } else {
            System.out.println(ui.list());
            System.out.println(tl.printOut());
            System.out.println(Ui.separator());
        }
    }

    /**
     * Returns a boolean indicating if the other object is an instance of ListCommand.
     *
     * @param other Another object
     * @return a boolean
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof ListCommand);
    }
}
