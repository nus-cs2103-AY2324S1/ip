package Duke.Commands;

import Duke.Exceptions.EmptyTaskListException;
import Duke.Tools.Storage;
import Duke.Tools.TaskList;
import Duke.Tools.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super("");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskListException {
        if (tasks.size() == 0) {
            throw new EmptyTaskListException();
        }
        ui.printTasks(tasks);
    }
}
