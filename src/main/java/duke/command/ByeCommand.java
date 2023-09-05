package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ByeCommand extends Command {

    public ByeCommand(ArrayList<String> commandDetails) {
        super(commandDetails);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByeCommand) {
            ByeCommand other = (ByeCommand) obj;
            if (this.commandDetails == null || other.commandDetails == null) {
                return false;
            }
            return this.commandDetails.equals(other.commandDetails);
        }
        return false;
    }
}
