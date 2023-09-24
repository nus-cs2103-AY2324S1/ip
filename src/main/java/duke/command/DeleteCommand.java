package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;
    private boolean isExit = false;

    /**
     * Constructs a DeleteCommand with the index of the task to be deleted.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand by removing the specified task from the task list.
     *
     * @param tasks   The list of tasks from which the task will be deleted.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for updating task data.
     * @throws DukeException If there is an issue executing the command.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert this.index < 0 : "Index cannot be less than 0!";

        if (index + 1 > tasks.size()) {
            throw new DukeException(ui.messageCard("The current number of tasks is " + tasks.size()
                    + ", " + "so you can't delete task " + (index + 1) + "!!."));
        }
        Task task = tasks.get(index);

        //remove task and updating
        tasks.deleteTask(index);
        storage.updateFile(tasks);
        ui.updateDeletedTask(task);

        //printing messages
        String res = "Noted. I've removed this task: \n" + task + "\nNow you have "
                + tasks.size() + " tasks in the list.";
        ui.updateMessage(res);
        ui.updateRecentCommand("delete");
    }

    /**
     * Checks if the DeleteCommand should trigger the program to exit.
     *
     * @return False since deleting a task does not trigger an exit.
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
