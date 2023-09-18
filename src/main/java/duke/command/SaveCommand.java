package duke.command;

import duke.Ui;
import duke.command.Command;
import duke.Storage;
import duke.task.*;

/**
 * Saves the current TaskList tasks to a file
 */
public class SaveCommand extends Command {
    protected String response;
    public SaveCommand(String response) {
        super();
        this.response = response;
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        storage.save(taskList);
        return taskList.toString();
    }
}
