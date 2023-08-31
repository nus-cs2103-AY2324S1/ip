package command;

import exception.DialogixException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DialogixException {
        List<Task> matchingTasks = taskList.findTasks(keyword);
        ui.showSearchResults(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
