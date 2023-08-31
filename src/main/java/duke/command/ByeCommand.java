package duke.command;

import duke.Command;
import duke.Ui;
import duke.Storage;
import duke.TaskList;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        ui.displayGoodbyeText();
    }
}
