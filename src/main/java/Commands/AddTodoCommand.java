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
        // Implement the execution of adding a todo task
        // Add the todo task to the task list, update UI, etc.
    }
}
