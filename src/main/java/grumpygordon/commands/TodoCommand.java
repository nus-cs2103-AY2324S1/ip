package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;
import grumpygordon.tasks.Todo;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {
    /**
     * Description of the todo task.
     */
    private final String description;

    /**
     * Constructor of TodoCommand.
     * @param description Description of the todo task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command.
     * @param tasks The list of tasks
     * @param storage The storage
     * @return The output string
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTask(new Todo(this.description, false));
        storage.saveTasks(tasks);
        return "Todo task added to list!\n" + tasks.getTask(tasks.size() - 1).toString();
    }
}
