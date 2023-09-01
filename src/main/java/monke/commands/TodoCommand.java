package monke.commands;

import monke.MonkeException;
import monke.Storage;
import monke.TaskList;
import monke.tasks.Todo;
import monke.Ui;

/**
 * The TodoCommand class represents a command to add a todo task to the Monke application.
 * It extends the Command class.
 */
public class TodoCommand extends Command {
    /** The command word for parser to recognize this command. */
    public static final String COMMAND_WORD = "todo";

    /** The description of the todo task to be added. */
    private String description;

    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the TodoCommand to add a todo task to the task list.
     *
     * @param ui The user interface.
     * @param storage The storage to read and write data.
     * @param tasks The list of tasks.
     * @throws MonkeException If data cannot be saved in the storage.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException {
        Todo todo = new Todo(this.description);
        tasks.add(todo);
        storage.saveData(tasks);
        ui.showAddTask(todo, tasks.size());
    }
}
