package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= 1 && index <= tasks.getSize()) {
            Task deletedTask = tasks.remove(index);
            System.out.println("\t Noted. I've removed this task:");
            System.out.println("\t   " + deletedTask.toString());
            System.out.println("\t Now you have " + tasks.getSize() + " tasks in the list.");
            storage.saveTasks(tasks);
        } else {
            throw new DukeException("Cannot delete a task that is out of range!");
        }
    }
}
