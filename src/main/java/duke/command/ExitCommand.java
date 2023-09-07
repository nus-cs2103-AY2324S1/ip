package duke.command;

import duke.task.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

public class ExitCommand extends Command{

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.printMessage("\nBye. Hope to see you again soon!");
    }
}
