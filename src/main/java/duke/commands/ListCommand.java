package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        ui.showList(tasks.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
