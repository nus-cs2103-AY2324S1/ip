package duke.command;

import duke.RichieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A ListCommand class
 */
public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws RichieException {
        int length = taskList.getSize();
        String result = "";
        for (int i = 0; i < length; i++) {
            result += (i + 1) + "." + taskList.getTask(i).toString() + "\n";
        }
        ui.showMessage(result);
    }
}


