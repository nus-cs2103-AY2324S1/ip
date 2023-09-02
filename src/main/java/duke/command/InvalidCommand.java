package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super(CommandType.INVALID);
    }

    public void execute(TaskList tasks, Ui ui) {
        ui.showInvalidCommandMessage();
    }
}
