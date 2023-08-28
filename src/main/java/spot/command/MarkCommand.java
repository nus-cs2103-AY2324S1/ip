package spot.command;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;

public class MarkCommand extends Command {

    private int position;

    public MarkCommand(int position) {
        this.position = position;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        tasks.markTask(position);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
