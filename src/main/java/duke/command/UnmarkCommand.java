package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class UnmarkCommand extends Command {
    int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= 1 && index <= tasks.getSize()) {
            tasks.unmarkAsDone(index);
            ui.showLine();
            System.out.println("\t OK, I've marked this task as not done yet:");
            System.out.println("\t   " + tasks.getTaskString(index));
            ui.showLine();
            storage.saveTasks(tasks);
        } else {
            throw new DukeException("Cannot unmark a task that is out of range!");
        }
    }
}
