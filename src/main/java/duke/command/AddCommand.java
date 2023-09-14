package duke.command;

import duke.task.TaskStorage;

/**
 * Represents a command to add a new {@code Task}
 */
public class AddCommand extends Command {
    private String task;

    public AddCommand(String task) {
        this.task = task;
    }

    @Override
    public String execute(TaskStorage taskStorage) {
        return taskStorage.save(task);
    }
}
