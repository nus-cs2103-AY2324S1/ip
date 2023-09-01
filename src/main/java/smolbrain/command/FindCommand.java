package smolbrain.command;

import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.task.TaskList;

public class FindCommand implements Command {

    private String keyword;
    private boolean loading;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the matching tasks in your list: ");
        tasks.findTasks(this.keyword, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void setLoading() {
        this.loading = true;
    }

}
