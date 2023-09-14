package duke.command;


import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * A command class to add a todo task.
 */
public class TodoCommand extends Command {
    private String description;
    private String location;

    /**
     * Constructs a TodoCommand with the description for todo.
     *
     * @param description
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Constructs a TodoCommand with the description for todo and its location.
     *
     * @param description
     * @param location
     */
    public TodoCommand(String description, String location) {
        this.description = description;
        this.location = location;
    }

    /**
     * Executes the command. Adds a todo task to the tasks TaskList.
     *
     * @param tasks   The list of tasks stored in TaskList object.
     * @param ui      The Ui object to interact with user.
     * @param storage The object used to store the tasks in case of changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.description == null) {
            throw new DukeException("Description of todo cannot be empty");
        }
        Task task = new Todo(description, this.location);
        tasks.addTask(task);
        ui.display("    Added Todo to the list of tasks:");
        ui.display("    " + task);
        ui.display("    You currently have " + tasks.getSize() + " tasks in the list.");
        try {
            storage.save(tasks.saveToStorage());
        } catch (IOException e) {
            throw new DukeException(
                    "Something went wrong while trying to save the tasks to the disk!");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof TodoCommand)) {
            return false;
        }

        TodoCommand o = (TodoCommand) other;
        return this.description.equals(o.description);
    }
}
