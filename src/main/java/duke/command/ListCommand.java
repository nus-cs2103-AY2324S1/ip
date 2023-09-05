package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;


public class ListCommand extends Command {

    public ListCommand(ArrayList<String> commandDetails) {
        super(commandDetails);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ListCommand) {
            ListCommand other = (ListCommand) obj;
            if (this.commandDetails == null || other.commandDetails == null) {
                return false;
            }
            return this.commandDetails.equals(other.commandDetails);
        }
        return false;
    }
}
