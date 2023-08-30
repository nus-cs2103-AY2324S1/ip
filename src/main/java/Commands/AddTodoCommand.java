package Commands;

import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

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
