package duke.command;

import duke.task.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents a command to add a new task to the task list.
 */
public class AddCommand extends Command {

    private final String taskDescription;

    /**
     * Creates a new AddCommand with the specified task description.
     *
     * @param taskDescription The description of the task to be added.
     */
    public AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(this.taskDescription, storage, ui);
    }
}
