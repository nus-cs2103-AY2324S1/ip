package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class DeleteCommand extends Command {
    int index;
    public DeleteCommand(String cmd) throws DukeException {
        try {
            int index = Integer.parseInt(cmd);
            this.index = index-1;
        } catch (NumberFormatException e) {
            throw new DukeException("Specify your task number clearly. (eg. delete 1)");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String deleteMessage = tasks.delete(index);
            storage.writeData(tasks.toWriteString());
            return deleteMessage;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Wow, that's a nonexistent task. Knew I shouldn't have put it past you. "
                    + "Check your tasks again with 'list'.");
        }
    }
}
