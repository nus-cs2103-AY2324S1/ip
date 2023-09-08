package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;

/**
 * Represents a command to add a new To-Do task.
 * Extends the {@link AddCommand} class.
 */
public class AddTodo extends AddCommand {

    /**
     * Initializes an command.AddTodo command with the specified input.
     *
     * @param input The user input for creating the To-Do task.
     */
    public AddTodo(String input) {
        super(input);
    }

    /**
     * Executes the command.AddTodo command to add a new To-Do task to the task list.
     *
     * @param tasks   The {@link TaskList} containing the list of tasks.
     * @param ui      The {@link Ui} for user interface interactions.
     * @param storage The {@link Storage} for loading and saving tasks.
     * @throws DukeException If there is an error in adding the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // Check if the description is empty or too short
            if (input.length() <= 5 || input.substring(5).trim().isEmpty()) {
                throw new DukeException("The description of a To-Do task cannot be empty.");
            }

            // Extract the description from the user input
            String description = input.substring(5).trim();

            // Create a new To-Do task and add it to the task list
            tasks.add(new Todo(description));

            // Display a success message
            ui.showAddedTask(tasks.getList().get(tasks.size() - 1));
        } catch (DukeException e) {

            // Handle exception.DukeException by displaying an error message
            throw new DukeException("The description of a To-Do task cannot be empty.");

        }
    }
}
