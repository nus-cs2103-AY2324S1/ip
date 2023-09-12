package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.DukeList;

/**
 * The MarkCommand class represents a command for marking tasks as done or undone in the task list.
 *
 */
public class MarkCommand extends Command {
    private boolean toMark;

    private String description;

    /**
     * Constructs a MarkCommand with the specified task details and whether to mark as done or undone.
     *
     * @param description   The details of the task to be marked.
     * @param toMark Whether to mark the task as done (true) or undone (false).
     */
    public MarkCommand(String description, boolean toMark) {
        this.description = description;
        this.toMark = toMark;
    }

/**
 * Executes the MarkCommand by changing the done status of a task and saving it to storage.
 *
 * @param dukelist The task list to which the task belongs.
 * @param storage  The storage object used for saving tasks.
 * @return A message indicating the successful change in task done status.
 * @throws DukeException If there is an error executing the command.
 */
    @Override
    public String execute(DukeList dukelist, Storage storage) throws DukeException {
        Task taskToChange;
        try {
            taskToChange = dukelist.setTaskStatus(this.description, this.toMark);
            assert taskToChange != null : "Task to be marked cannot be null hehe";
            storage.saveData(dukelist.getList());
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        return Ui.printMarked(taskToChange, this.toMark);
    }
}