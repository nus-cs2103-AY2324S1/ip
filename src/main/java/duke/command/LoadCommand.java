package duke.command;

import duke.Ui;
import duke.command.Command;
import duke.exceptions.InvalidFileTypeException;
import duke.Storage;
import duke.task.*;

/**
 * Loads the tasks from a given file
 */
public class LoadCommand extends Command {
    protected String response;
    public LoadCommand(String response) {
        super();
        this.response = response;
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        try {
            taskList.setTasks(storage.load());
            return taskList.toString();
        } catch (InvalidFileTypeException e) {
            return ui.format_response(e.getMessage());
        }
    }
}
