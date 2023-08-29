package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class UnMarkCommand extends Command {
    private int index;

    public UnMarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("OOPS! The index to unmark is invalid!");
        }
        taskList.markTask(index);
        ui.printMessage("Nice! I've marked this task as not completed:\n" + taskList.get(index));
        storage.saveList(taskList.getAllTasks());
    }
}