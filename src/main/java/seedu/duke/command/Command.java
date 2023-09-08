package seedu.duke.command;

import seedu.duke.TaskList;
import seedu.duke.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, String[] response) throws Exception;
}
