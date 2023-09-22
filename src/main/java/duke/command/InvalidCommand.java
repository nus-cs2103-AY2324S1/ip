package duke.command;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class InvalidCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return ui.invalidTaskMessage();
    }
}
