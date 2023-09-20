package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


/**
 * Command that adds a task to the task List.
 */
public class AddCommand extends Command {
    /** Type of task to add */
    private final String taskType;
    /** The other information required to instantiate a task.Task */
    private final String[] args;

    /**
     * Constructs a new Add command.
     *
     * @param taskType Type of task to add.
     * @param args Additional information required for different tasks.
     */
    public AddCommand(String taskType, String[] args) {
        this.taskType = taskType;
        this.args = args;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) {
        return tasks.addTask(taskType, args);
    }

}
