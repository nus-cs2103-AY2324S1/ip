package dude.command;

import dude.Storage;
import dude.exception.DudeException;
import dude.task.Task;
import dude.task.TaskList;

/**
 * Command to mark task as done.
 */
public class MarkTaskCommand extends DudeCommand {
    private static final String MARKED_AS_DONE_PREFIX = "Nice! I've marked this task as done:\n\t";

    /**
     * Task to mark.
     */
    private final int taskIndex;

    /**
     * Constructor for mark task command.
     *
     * @param taskIndex Index of task to mark.
     */
    public MarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks task as done and saves list to disk.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DudeException {
        Task task = taskList.getTask(taskIndex);
        assert task != null : "Task should not be null.";
        task.markAsDone();
        storage.save(taskList.toArrayList());
        return String.format(MARKED_AS_DONE_PREFIX + task);
    }

    /**
     * {@inheritDoc}
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
