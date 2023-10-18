package commands;

import data.TaskList;
import data.exception.StorageException;
import data.tasks.Task;
import data.tasks.Todo;
import storage.Storage;
import ui.UiMessage;

/**
 * The TodoCommand class.
 * Handles creating a new {@link Todo}.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * The constructor method of the TodoCommand class.
     * Takes in a description of a {@link Todo}.
     *
     * @param description The description of a todo.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public UiMessage execute(
            TaskList tasks, Storage storage) throws StorageException {
        Task todo = new Todo(description);
        tasks.add(todo);
        storage.update(tasks);
        return new UiMessage(new String[] {
            "Okie! I've added a new TODO:",
            "  " + todo,
            "Total no. of tasks stored: " + tasks.getSize()
        });
    }
}
