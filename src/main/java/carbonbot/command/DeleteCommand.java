package carbonbot.command;

import java.io.IOException;

import carbonbot.DukeException;
import carbonbot.Storage;
import carbonbot.TaskList;
import carbonbot.Ui;
import carbonbot.task.Task;

public class DeleteCommand extends Command {
    private final int taskIdx;

    public DeleteCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.get(taskIdx);
            tasks.delete(taskIdx);

            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage(task.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException ie) {
            throw new DukeException("Index provided was out-of-bounds. Use the index"
                    + " number labelled for the task in the command 'list'!");
        }

        try {
            storage.write(tasks.serialize());
        } catch (IOException ex) {
            throw new DukeException("I/O Error: Failed to write to storage. "
                    + ex.getMessage());
        }
    }
}
