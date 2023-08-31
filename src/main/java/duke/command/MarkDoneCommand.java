package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * The MarkDoneCommand class.
 */
public class MarkDoneCommand extends Command {
    private int index;

    /**
     * Constructor for the duke.command.MarkDoneCommand class.
     *
     * @param index Index of the task to be marked.
     */
    public MarkDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            taskList.markDone(index);
            Task task = taskList.getTask(index);
            storage.rewriteToFile(taskList.getList());
            ui.successfulMarkDoneMsg(task.displayableForm());
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
