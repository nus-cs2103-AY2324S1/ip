package duke.command;


import duke.RichieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class that handles exiting the application
 */
public class ExitCommand extends Command {

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws RichieException {
        ui.showBye();
    }
}
