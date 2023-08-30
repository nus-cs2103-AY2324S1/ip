package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class MarkCommand extends Command {

    int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= 1 && index <= tasks.getSize()) {
            tasks.markAsDone(index);
            System.out.println("\t Nice! I've marked this task as done:");
            System.out.println("\t   " + tasks.getTaskString(index));
            storage.saveTasks(tasks);
        } else {
            throw new DukeException("Cannot mark a task that is out of range!");
        }
    }
}
