package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import duke.exception.DukeException;

public class DeleteCommand extends Command {
    private int index; //index of task to be deleted

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.get(index);
            tasks.delete(index);
            ui.printDeleteMessage(removedTask, tasks.size());
            storage.saveTasksToFile(tasks);
        } else {
            throw new DukeException("☹ OOPS!!! Please provide a valid task index to delete.");
        }
    }
}
