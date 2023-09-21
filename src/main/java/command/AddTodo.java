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

    private static final int DESCRIPTION_START_INDEX = 5;

    /**
     * Initializes a command.AddTodo command with the specified input.
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
     * @return A string representation of the task added message and the added task details.
     * @throws DukeException If there is an error in adding the task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (isDescriptionEmptyOrTooShort(input)) {
            throw new DukeException("The description of a To-Do task cannot be empty.");
        }

        String description = extractDescriptionFromInput(input);
        Todo todo = new Todo(description);
        tasks.add(todo);

        return buildResponseMessage(ui, todo);
    }

    private boolean isDescriptionEmptyOrTooShort(String input) {
        return input.length() <= DESCRIPTION_START_INDEX || input.substring(DESCRIPTION_START_INDEX).trim().isEmpty();
    }

    private String extractDescriptionFromInput(String input) {
        return input.substring(DESCRIPTION_START_INDEX).trim();
    }

    private String buildResponseMessage(Ui ui, Todo todo) {
        StringBuilder response = new StringBuilder();
        response.append(ui.showAddedTask(todo));
        response.append("\n").append(todo);
        return response.toString();
    }
}
