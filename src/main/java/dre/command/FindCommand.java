package dre.command;

import dre.exception.DreException;
import dre.storage.Storage;
import dre.task.TaskList;
import dre.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DreException {
        TaskList foundTasks = tasks.findTasksByKeyword(keyword);
        ui.showFoundTasks(foundTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}