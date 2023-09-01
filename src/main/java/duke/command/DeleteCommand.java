package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a Delete command to be executed.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Executes the list of commands to delete a Task from a TaskList.
     *
     * @param taskList The given TaskList with the Task to be deleted.
     * @param ui The given Ui to show the status of the deletion command.
     * @param storage The given Storage that saves the TaskList locally.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.remove(this.index);
        ui.addDeleteMessage();
        ui.addTaskMessage(task);
        ui.addTaskListSizeMessage(taskList);
    }
}
