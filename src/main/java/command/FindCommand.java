package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;

public class FindCommand extends Command {
    private String query;

    public FindCommand(String fullCommand) {
        super(false);
        int split = fullCommand.indexOf(" ");
        this.query = fullCommand.substring(split + 1);
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        ui.showQueryList(taskList.findTask(this.query));
    }
}
