package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        ui.printBye();
        storage.writeFile(taskList);
    }
}
