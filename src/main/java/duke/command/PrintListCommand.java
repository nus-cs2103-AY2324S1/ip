package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class PrintListCommand extends Command {
    public PrintListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList);
    }
}
