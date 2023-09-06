package puke.command;

import puke.managers.DataHandler;
import puke.managers.PukeException;
import puke.managers.TaskList;
import puke.managers.Ui;
import puke.task.ToDo;

/**
 * A Command class that when executed, creates a new Todo task
 */
public class TodoCommand extends Command {
    private final String desc;

    /**
     * Creates a new ToDoCommand
     * @param rest the rest of the input line.
     */
    public TodoCommand(String rest) {
        super(false, !rest.isEmpty());
        this.desc = rest;
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
            tl.add(new ToDo(this.desc));
            System.out.println(ui.toDo(tl));
            System.out.println(Ui.separator());
            DataHandler.writeToDatabase(tl);
        } catch (Exception PukeException) {
            System.out.println(Ui.errorMessage());
            System.out.println(Ui.separator());
        }
    }

    /**
     * Returns a boolean indicating if the other object has the same toString as this command and is an instance of
     * TodoCommand.
     *
     * @param other Another object.
     * @return A boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof TodoCommand && other.toString().equals(this.toString()));
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
            return new ToDo(this.desc).toString();
        } catch (PukeException e) {
            throw new RuntimeException(e);
        }
    }
}
