package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            taskList.markNotDone(index);
            Task task = taskList.getTask(index);
            storage.rewriteToFile(taskList.getList());
            ui.successfulMarkNotDoneMsg(task.displayableForm());
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}