package duke.command;

import duke.Ui;
import duke.Storage;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidDateTimeFormatException;
import duke.task.TaskList;

public class ByeCommand extends Command {

    public ByeCommand() {}

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        return "Bye!";
    }


}
