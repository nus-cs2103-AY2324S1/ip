package puke.command;

import puke.DataHandler;
import puke.TaskList;
import puke.Ui;

/**
 * A Command class that when executed, prints the message to mark a task as complete.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a new MarkCommand
     * @param rest the rest of the input line.
     */
    public MarkCommand(String rest) {
        super(false, true);
        this.index = Integer.parseInt(rest);
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
            tl.mark(this.index);
            System.out.println(ui.mark(this.index));
            System.out.println(Ui.separator());
            DataHandler.writeToDatabase(tl);
        } catch (Exception PukeException) {
            System.out.println(Ui.errorMessage());
            System.out.println(Ui.separator());
        }
    }

    /**
     * Returns a boolean indicating if the other object has the same toString() and is an instance of MarkCommand.
     *
     * @param other Another object
     * @return a boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof MarkCommand && other.toString().equals(this.toString()));
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
