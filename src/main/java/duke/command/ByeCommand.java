package duke.command;

import duke.ui.Ui;

import duke.task.TaskList;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(CommandType.BYE);
    }

    public void execute(TaskList tasks, Ui ui) {
        ui.endMessage();
    }
}
