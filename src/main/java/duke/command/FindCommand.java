package duke.command;

import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> allTasks = tasks.getAllTasks();
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : allTasks) {
            boolean hasKeyword = task.toString().contains(keyword);

            if (hasKeyword) {
                filteredTasks.add(task);
            }
        }
        ui.showFilteredList(filteredTasks);
    }
}
