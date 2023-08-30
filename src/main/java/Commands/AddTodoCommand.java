package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Tasks.Task;
import Ui.Ui;

public class AddTodoCommand implements Command {
    public static final String TODO_PATTERN = "^(todo)\\s+.+";

    private Task task;

    public AddTodoCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.addLine(task.toString());
    }
}
