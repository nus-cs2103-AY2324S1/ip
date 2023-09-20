package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class UnmarkCommand extends Command {
    int index;
    public UnmarkCommand(String cmd) throws DukeException {
        try {
            int index = Integer.parseInt(cmd);
            this.index = index-1;
        } catch (NumberFormatException e) {
            throw new DukeException("Specify your task number clearly. (eg. unmark 1)");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String returnMessage = tasks.getTask(index).markAsUndone();
            storage.writeData(tasks.toWriteString());
            return returnMessage;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Wow, that's a nonexistent task. Check your tasks again with 'list'.");
        }
    }
}
