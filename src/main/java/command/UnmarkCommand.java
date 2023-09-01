package Command;

import duke.*;

import java.util.ArrayList;

public class UnmarkCommand extends Command{
    public static final String COMMAND_UNMARK = "unmark";
    public UnmarkCommand(ArrayList<String> params) {
        super(params);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(params.get(1)) - 1;
        Task task = tasks.unmark(index);
        ui.printTaskUnmarkedMessage(task);
        tasks.saveState(storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
