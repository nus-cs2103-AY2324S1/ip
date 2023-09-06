package puke.command;

import puke.managers.DataHandler;
import puke.managers.PukeException;
import puke.managers.TaskList;
import puke.managers.Ui;
import puke.task.Deadline;

/**
 * A Command class that when executed, creates a Deadline Task
 */
public class DeadlineCommand extends Command {
    private final String[] rest;

    /**
     * Creates a new DeadlineCommand
     * @param rest the rest of the input line.
     */
    public DeadlineCommand(String rest) {
        super(false, !rest.isEmpty());
        this.rest = rest.split(" /");
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is in the wrong format, prints an error message instead.
     *
     * @param tl The task list.
     * @param ui The UI.
     */
    public void execute(TaskList tl, Ui ui) {
        try {
            tl.add(new Deadline(this.rest));
            System.out.println(ui.deadline(tl));
            System.out.println(Ui.separator());
            DataHandler.writeToDatabase(tl);
        } catch (Exception PukeException) {
            System.out.println(Ui.errorMessage());
            System.out.println(Ui.separator());
        }
    }

    /**
     * Returns a boolean indicating if the other object has the same toString as this command and is an instance of
     * DeadlineCommand.
     *
     * @param other Another object.
     * @return A boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof DeadlineCommand && other.toString().equals(this.toString()));
    }

    /**
     * Returns a String representation of this command.
     *
     * @return a String.
     * @throws RuntimeException If an incorrect format is used
     */
    @Override
    public String toString() {
        try {
            return new Deadline(this.rest).toString();
        } catch (PukeException e) {
            throw new RuntimeException(e);
        }
    }
}
