package buddy.commands;

import buddy.TaskList;
import buddy.Todo;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * The class represents the command for adding a Todo into the task list.
 */
public class AddTodoCommand extends Command {
    public static final String MESSAGE_FORMAT =
            "todo <description>\n" + "Example: todo eat medicine";
    private String description;

    /**
     * The constructor for an AddTodoCommand.
     *
     * @param description The description of the todo.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description, false);
        tasks.addTask(todo);
        String response = ui.printAddSuccessMessage(todo, tasks);
        storage.writeToFile(tasks.getAllTasks());
        return response;
    }
}
