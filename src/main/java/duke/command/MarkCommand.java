package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeTaskNotFoundException;

/**
 * Represents a command that marks a task as done.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a MarkCommand object.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command: marks the task as done and displays a "Done" message.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage.
     * @return The done message.
     * @throws DukeTaskNotFoundException If the task to be marked as done does not exist.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeTaskNotFoundException {
        try {
            return String.format("Nice! I've marked this task as done:\n  %s\n",
                    tasks.getTask(taskIndex).markAsDone());
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
