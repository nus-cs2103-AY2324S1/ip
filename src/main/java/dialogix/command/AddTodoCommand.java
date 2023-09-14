package dialogix.command;

import dialogix.exception.DialogixException;
import dialogix.main.Storage;
import dialogix.main.TaskList;
import dialogix.main.Ui;
import dialogix.task.Todo;

/**
 * Represents a command to add a "todo" task to the task list.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructs an AddTodoCommand with a description for the "todo" task.
     *
     * @param description The description of the "todo" task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the AddTodoCommand by adding a "todo" task to the task list and saving the updated list.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws DialogixException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DialogixException {
        tasks.addToStack();
        Todo todo = new Todo(description);
        tasks.add(todo);
        ui.printAddSuccessMessage(todo, tasks.size());
        storage.save(tasks.getAllTasks());
    }
}
