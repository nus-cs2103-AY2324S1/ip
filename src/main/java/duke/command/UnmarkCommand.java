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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= 0 && index < tasks.size()) {
            Task unmarkTask = tasks.get(index);
            tasks.markNotDone(index);
            ui.printMessage("OK, I've marked this task as not done yet:\n\t",unmarkTask);
            storage.saveTasksToFile(tasks);
        } else {
            throw new DukeException("☹ OOPS!!! Please provide a valid task index to be unmarked.");
        }
    }
}
