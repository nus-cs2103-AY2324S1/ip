package command;

import duke.DiskManager;
import duke.DukeException;
import duke.TaskManager;
import duke.Ui;
import task.Todo;

/**
 * Represents a to-do command where when executed, adds a to-do task to the task list.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Constructs a TodoCommand with the description provided.
     * @param description The description of the task to be added.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskManager taskManager, DiskManager diskManager, Ui ui) throws DukeException {
        ui.printOutput(taskManager.addTask(new Todo(description)));
        diskManager.saveToDisk(taskManager);
    }
}
