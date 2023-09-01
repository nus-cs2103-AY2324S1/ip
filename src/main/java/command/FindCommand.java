package command;

import duke.Storage;
import duke.Ui;

import java.util.ArrayList;

import task.Task;
import task.TaskList;

public class FindCommand extends Command {
    protected String keyword;
    public static final String COMMAND_WORD = "find";

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredTasks = tasks.filterTasks(this.keyword);
        if (filteredTasks.isEmpty()) {
            ui.showMessage("No matching tasks found");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            ui.printTaskList(filteredTasks);
        }
    }
}
