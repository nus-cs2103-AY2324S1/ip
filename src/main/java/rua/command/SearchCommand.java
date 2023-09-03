package rua.command;

import rua.common.Storage;
import rua.common.Ui;
import rua.task.TaskList;

public class SearchCommand implements Command {
    private final String keyword;

    public SearchCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.showMessage(" Here are the matching tasks in your list:\n");
        String result = tasks.search(keyword);
        ui.showMessage(result);
        return tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof SearchCommand)) {
            return false;
        }

        SearchCommand c = (SearchCommand) o;
        return c.keyword.equals(this.keyword);
    }
}
