package spot.command;

import spot.exception.SpotException;
import spot.Storage;
import spot.TaskList;
import spot.Ui;

public class DeleteCommand extends Command {

    private int position;

    public DeleteCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        tasks.deleteTask(position);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
