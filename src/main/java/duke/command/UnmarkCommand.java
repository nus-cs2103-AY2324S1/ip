package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

public class UnmarkCommand extends Command {
    private int index; //index of task to mark
    public UnmarkCommand(int index) {
        this.index = index - 1;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index >= 0 && index < taskList.size()) {
            Task unmarkTask = taskList.get(index);
            taskList.markNotDone(index);
            ui.printMessage("OK, I've marked this task as not done yet:\n\t",unmarkTask);
            storage.saveTasksToFile(taskList);
        } else {
            throw new DukeException("☹ OOPS!!! Please provide a valid task index to be unmarked.");
        }
    }
}
