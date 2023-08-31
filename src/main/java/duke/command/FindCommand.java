package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class FindCommand extends Command {

    private String commandBody;

    public FindCommand(String commandBody) {
        super(false);
        this.commandBody = commandBody;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.findTask(commandBody, ui);
    }
}
