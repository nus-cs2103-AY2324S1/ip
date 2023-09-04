package duke.command;

import duke.Ui;
import duke.command.Command;
import duke.Storage;
import duke.task.*;

/**
 * Prints the taskList
 */
public class PrintListCommand extends Command {
    public PrintListCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        System.out.println(taskList);
        ui.printLine();
    }
}
