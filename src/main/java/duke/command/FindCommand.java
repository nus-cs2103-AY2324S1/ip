package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(this.keyword)) {
                filteredTasks.add(task);
            }
        }
        ui.showFilteredTaskList(filteredTasks);
    } 
}
