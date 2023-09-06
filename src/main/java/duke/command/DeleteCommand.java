package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeTaskNotFoundException;
import duke.task.Task;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a DeleteCommand object.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command: deletes the task from the list of tasks and displays a "Deleted" message.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage.
     * @return The deleted message.
     * @throws DukeTaskNotFoundException If the task to be deleted does not exist.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeTaskNotFoundException {
        try {
            Task task = tasks.getTask(taskIndex);
            tasks.removeTask(taskIndex);
            return String.format("Noted. I've removed this task:\n  %s\n Now you have %d task%s in the list.",
                    task, tasks.getSize(), tasks.getSize() == 1 ? "" : "s");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeTaskNotFoundException();
        }
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
