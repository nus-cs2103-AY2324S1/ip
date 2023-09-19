package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.TaskType;

/**
 * Represents a command to add an event task.
 */
public class AddEventCommand extends Command {

    /**
     * Constructs a new AddEventCommand object.
     *
     * @param taskList The current list of tasks.
     * @param args     The arguments supplied by the user.
     */
    public AddEventCommand(TaskList taskList, String args) {
        super(CommandType.ADD_EVENT, taskList, args);
    }

    /**
     * Adds an event task to the list of tasks.
     *
     * @return The response to the user.
     */
    @Override
    public String execute() {
        return this.taskList.addTask(TaskType.EVENT, args);
    }
}
