package duke.command;

import java.io.IOException;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * The MarkNotDoneCommand class.
 */
public class MarkNotDoneCommand extends Command {
    private int index;

    /**
     * Constructor for the duke.command.MarkNotDoneCommand class.
     *
     * @param index Index of the task to be marked.
     */
    public MarkNotDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            taskList.markNotDone(index);
            Task task = taskList.getTask(index);
            storage.rewriteToFile(taskList.getList());
            return ui.successfulMarkNotDoneMsg(task.displayableForm());
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
