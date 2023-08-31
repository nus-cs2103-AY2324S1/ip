package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        store.saveTasks(tasks.taskList);
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
