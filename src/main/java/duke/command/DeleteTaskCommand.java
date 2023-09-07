package duke.command;

import java.io.IOException;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * The DeleteTaskCommand class.
 */
public class DeleteTaskCommand extends Command {
    private int index;

    /**
     * Constructor for the duke.command.DeleteTaskCommand class.
     *
     * @param index Index of the task to be deleted.
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = taskList.getTask(index);
            taskList.delete(index);
            storage.rewriteToFile(taskList.getList());
            return ui.successfulTaskDeletionMsg(task.displayableForm(), taskList.getSize());
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
