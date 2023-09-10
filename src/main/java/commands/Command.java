package commands;

import tasks.TaskList;
import ui.Ui;

/**
 * This class encapsulates a command given to the Duke application to run a certain set of instructions.
 */
public class Command {

    /**
     * Execute the set of instructions based on the Command subclass
     *
     * @param tasks A TaskList object containing all tasks in the task list.
     */
    public String execute(TaskList tasks) {
        return "done";
    }
}
