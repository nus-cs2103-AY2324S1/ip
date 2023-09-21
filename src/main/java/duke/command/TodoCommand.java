package duke.command;

import java.io.IOException;

import duke.*;

/**
 * Represents a command to create a new Todo task.
 * This command, when executed, will add a new Todo task to the task list and
 * persistently store the updated list to a data file.
 */
public class TodoCommand extends Command {

    /** The description or detail of the Todo task. */
    private String description;

    /**
     * Initializes a new instance of the TodoCommand class.
     *
     * @param description The description of the Todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the Todo command by creating a new Todo task and adding it to the task list.
     * This command will update both the in-memory task list and the stored data file.
     *
     * @param tasks   The list of tasks.
     * @param ui      The UI object used to communicate with the user.
     * @param storage The storage object responsible for reading from and writing tasks to the data file.
     * @return A string message indicating the task has been added.
     * @throws DukeException If the description of the todo is empty.
     * @throws IOException If there's an error when saving the tasks to the data file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        Todo newTodo = new Todo(description);
        tasks.add(newTodo);

        try {
            storage.saveTasks(tasks); // Save the updated tasks to file
        } catch (IOException e) {
            return ui.showSavingError(e.getMessage());
        }

        return ui.showAddedTask(newTodo, tasks.size());
    }
}