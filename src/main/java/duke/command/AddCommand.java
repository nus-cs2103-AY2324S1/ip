package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;


/**
 * Represents a command that adds a task to the existing task list.
 */
public class AddCommand extends Command {
    protected Task taskToBeAdded;

    public AddCommand(Task taskToBeAdded) {
        this.taskToBeAdded = taskToBeAdded;
    }

    /**
     * Modifies the existing list by adding the task provided by the user.
     * @param taskList the existing task list of the user
     * @param ui the ui that handles successful/unsuccessful messages
     */
    public String execute(TaskList taskList, Ui ui) {
        taskList.addTask(this.taskToBeAdded);
        return ui.showAddedMessage(taskToBeAdded, taskList);
    }

}
