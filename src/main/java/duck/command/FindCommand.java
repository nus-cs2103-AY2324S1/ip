package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.Ui;
import duck.task.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        ui.showMatchingTasksMessage(tasks.find(keyword));
    }
}
