package spot.command;

import spot.exception.SpotException;
import spot.Storage;
import spot.TaskList;
import spot.Ui;

public class FindCommand extends Command {

    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTasks(ui, keyword);
    };

    @Override
    public boolean isExit() {
        return false;
    }
}
