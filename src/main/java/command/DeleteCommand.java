package Command;

import duke.*;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    public static final String COMMAND_DELETE = "delete";
    public DeleteCommand(ArrayList<String> params) {
        super(params);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(params.get(1)) - 1;
        Task task = tasks.remove(index);
        ui.printTaskDeletedMessage(task, tasks.getTaskCount());
        tasks.saveState(storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
