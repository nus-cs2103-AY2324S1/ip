package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private int index;
    private boolean isExit = false;

    /**
     * Constructs a MarkCommand with the index of the task to be marked as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the MarkCommand by marking the specified task as done in the task list.
     *
     * @param tasks   The list of tasks to which the task's status will be updated.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for updating task data.
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert this.index < 0 : "Index cannot be less than 0!";

        if (this.index + 1 > tasks.size()) {
            throw new DukeException("The current number of tasks is " + tasks.size()
                    + ", so you can't mark task " + (index + 1) + "!!.");
        }
        //update
        Task task = tasks.get(index);
        tasks.markTask(index);
        storage.updateFile(tasks);
        ui.updateLatestMarked(index);

        String type = findType(task);
        String res = "Nice! I've marked this task as done:" + "\n"
                + "[" + type + "]" + "[" + task.getStatusIcon() + "] "
                + task.getDescription();
        ui.updateMessage(res);
        ui.updateRecentCommand("mark");
    }

    private String findType(Task task) {
        if (task instanceof Todo) {
            return "T";
        } else if (task instanceof Deadline) {
            return "D";
        } else if (task instanceof Event) {
            return "E";
        }

        return "";
    }

    /**
     * Checks if the MarkCommand should trigger the program to exit.
     *
     * @return False since marking a task as done does not trigger an exit.
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
