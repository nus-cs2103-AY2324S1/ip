package Command;

import duke.*;

import java.util.ArrayList;

public class MarkCommand extends Command {
    public static final String COMMAND_MARK = "mark";
    public MarkCommand(ArrayList<String> params) {
        super(params);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(params.get(1)) - 1;
        Task task = tasks.mark(index);
        ui.printTaskMarkedMessage(task);
        tasks.saveState(storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
