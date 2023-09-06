package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.TaskType;

/**
 * Represents a command to add a todo task.
 */
public class AddToDoCommand extends Command {

    /**
     * Constructs a new AddToDoCommand object.
     *
     * @param taskList The current list of tasks.
     * @param args     The arguments supplied by the user.
     */
    public AddToDoCommand(TaskList taskList, String args) {
        super(CommandType.ADD_TODO, taskList, args);
    }

    /**
     * Adds a todo task to the list of tasks.
     */
    @Override
    public String execute() {
        return this.taskList.addTask(TaskType.TODO, args);
    }

}
