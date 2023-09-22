package dude.command;

import dude.Storage;
import dude.exception.DudeException;
import dude.task.Task;
import dude.task.TaskList;

/**
 * Command to add task to Dude.
 */
public class AddTaskCommand extends DudeCommand {
    private static final String ADDED_TASK_MSG = "Got it! I've added this task:\n\t%s\n"
            + "You now have a total of %d task(s).";

    /**
     * Task to add.
     */
    private final Task task;

    /**
     * Constructs new add task command.
     *
     * @param task Task to add.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to task list and saves list to disk.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DudeException {
        assert task != null : "Task should not be null.";
        taskList.add(task);
        storage.save(taskList.toArrayList());
        return String.format(ADDED_TASK_MSG, task, taskList.getNumTasks());
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
