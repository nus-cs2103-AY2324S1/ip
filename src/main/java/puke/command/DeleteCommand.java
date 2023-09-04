package puke.command;

import puke.DataHandler;
import puke.TaskList;
import puke.Ui;
import puke.task.Task;


/**
 * A Command class that when executed, deletes a task in the task list
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a new DeleteCommand
     * @param rest the rest of the input line.
     */
    public DeleteCommand(String rest) {
        super(false, true);
        this.index = Integer.parseInt(rest);
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is invalid due to the index being out of bounds, prints an error message instead.
     *
     * @param tl The task list.
     * @param ui The UI.
     */
    public void execute(TaskList tl, Ui ui) {
        try {
            Task hold = tl.delete(this.index);
            System.out.println(ui.delete(hold, tl));
            System.out.println(Ui.separator());
            DataHandler.writeToDatabase(tl);
        } catch (Exception PukeException) {
            System.out.println(Ui.errorMessage());
            System.out.println(Ui.separator());
        }
    }

    /**
     * Returns a boolean indicating if the other object has the same toString() as this one.
     *
     * @param other Another object.
     * @return a boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof DeleteCommand && other.toString().equals(this.toString()));
    }

    /**
     * Returns a string representing this command.
     *
     * @return a String
     */
    @Override
    public String toString() {
        return "delete " + this.index;
    }
}
