package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

import java.text.ParseException;

public class MarkCommand extends Command {
    int index;
    public MarkCommand(String cmd) throws DukeException {
        try {
            int idx = Integer.parseInt(cmd);
            this.index = idx-1;
        } catch (NumberFormatException e) {
            throw new DukeException("Specify your task number clearly. (eg. mark 1)");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String returnMessage = tasks.getTask(index).markAsDone();
            storage.writeData(tasks.toWriteString());
            return returnMessage;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Nonexistent task, though I wouldn't be surprised if you did something "
                    + "you didn't need to do all this time. "
                    + "Check your tasks again with 'list'.");
        }
    }
}
