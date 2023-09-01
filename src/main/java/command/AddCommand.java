package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


/**
 * Command that adds a task to the task.Task List.
 */
public class AddCommand extends Command {
    /** Type of task to add */
    private String taskType;
    /** The other information required to instantiate a task.Task */
    private String[] args;

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
    public void execute(TaskList tasks, Ui ui, Storage store) {
        String action = tasks.addTask(taskType, args);
        ui.respondUser(action);
    }

    @Override
    public boolean isExit() {
        return false;
    };
}
