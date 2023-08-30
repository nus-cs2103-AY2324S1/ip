package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Tasks.Task;
import Ui.Ui;

public class AddDeadlineCommand implements Command {
    private Task task;

    public AddDeadlineCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Implement the execution of adding a deadline task
        // Add the deadline task to the task list, update UI, etc.
    }
}
