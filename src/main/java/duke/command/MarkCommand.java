package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;
import duke.exception.DukeException;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command{
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (this.index + 1 > tasks.size()) {
            throw new DukeException(ui.messageCard("The current number of tasks is " + tasks.size() + ", " +
                    "so you can't mark task " + (index + 1) + "!!."));
        }
        //update
        Task task = tasks.get(index);
        tasks.markTask(index);
        storage.updateFile(tasks);

        String res = "Nice! I've marked this task as done:" + "\n"
                + "[" + task.getStatusIcon() + "] " + task.getDescription();
        ui.printMessage(res);
    }

    /**
     * Checks if the MarkCommand should trigger the program to exit.
     *
     * @return False since marking a task as done does not trigger an exit.
     */
    @Override
    public boolean isExit(){
        return this.isExit;
    }
}
