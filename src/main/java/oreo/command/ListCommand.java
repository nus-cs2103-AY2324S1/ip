package oreo.command;

import oreo.task.Task;
import oreo.task.TaskList;
import oreo.ui.Ui;

public class ListCommand extends Command {

    @Override
    public String executeEditMode(TaskList tasks, int index, Task oldTask) {
        return execute(tasks);
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.list();
    }
}
