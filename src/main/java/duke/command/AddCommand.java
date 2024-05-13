package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * Represents a command that adds a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Creates an AddCommand object.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command: adds the task to the list of tasks and displays an "Added" message.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage.
     * @return The added message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTask(task);
        return String.format("Got it. I've added this task:\n  %s\n Now you have %d task%s in the list.",
                task, tasks.getSize(), tasks.getSize() == 1 ? "" : "s");
    }

    /**
     * Returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
