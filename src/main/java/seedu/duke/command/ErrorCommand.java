package seedu.duke.command;

import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.DukeException;

public class ErrorCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, String[] response) throws Exception {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
