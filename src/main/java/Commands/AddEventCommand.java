package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Tasks.Task;
import UI.UI;

public class AddEventCommand implements Command {
    private Task task;

    public AddEventCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        // Implement the execution of adding an event task
        // Add the event task to the task list, update UI, etc.
    }
}
