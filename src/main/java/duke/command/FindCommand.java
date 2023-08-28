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
        TaskList findTasks = new TaskList();
        for (int i = 0; i < tasks.getCountTasks(); i++) {
            Task task = tasks.getTask(i);
            if (task.contains(this.keyword)) {
                findTasks.addTask(task);
            }
        }
        ui.printFindTasks(findTasks);
    }
}
