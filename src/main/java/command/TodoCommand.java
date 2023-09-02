package command;

import duke.DiskManager;
import duke.DukeException;
import duke.TaskManager;
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
    public String execute(TaskManager taskManager, DiskManager diskManager) throws DukeException {
        String res = taskManager.addTask(new Todo(description));
        diskManager.saveToDisk(taskManager);
        return res;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof TodoCommand) {
            TodoCommand temp = (TodoCommand) other;
            return temp.description.equals(this.description);
        }
        return false;
    }
}
