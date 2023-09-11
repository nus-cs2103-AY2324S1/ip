package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    /**
     * Index of the task to be deleted.
     */
    private final int index;

    /**
     * Constructor of DeleteCommand.
     * @param index Index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     * @param tasks The list of tasks
     * @param storage The storage
     * @return The output string
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String deletedTask = tasks.getTask(this.index).toString();
        tasks.deleteTask(this.index);
        storage.saveTasks(tasks);
        return "Task deleted from list!\n" + deletedTask;
    }
}
