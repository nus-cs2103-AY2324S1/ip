package puke.command;

import puke.managers.DataHandler;
import puke.managers.TaskList;
import puke.managers.Ui;

/**
 * A Command class that when executed, marks a task as undone.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates a new UnmarkCommand
     * @param rest the rest of the input line.
     */
    public UnmarkCommand(String rest) {
        super(false, true);
        try {
            this.index = Integer.parseInt(rest.substring(1));
        } catch (Exception e) {
            this.index = -1;
        }
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is in the wrong format or an index is out of bounds, prints an error message instead.
     *
     * @param tl The task list.
     * @param ui The UI.
     */
    public void execute(TaskList tl, Ui ui) {
        try {
            tl.unmark(this.index);
            System.out.println(ui.unmark());
            System.out.println(Ui.separator());
            DataHandler.writeToDatabase(tl);
        } catch (Exception e) {
            System.out.println(Ui.errorMessage());
            System.out.println(Ui.separator());
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
        return (other instanceof UnmarkCommand && other.toString().equals(this.toString()));
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
