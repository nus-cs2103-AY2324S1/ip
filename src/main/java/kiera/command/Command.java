package kiera.command;

import kiera.Storage;
import kiera.TaskList;
import kiera.Ui;
import kiera.tasktype.TaskType;

/**
 * Command types to perform specific actions in the application.
 */
public abstract class Command {
    private String desc;
    private TaskType t;
    public Command() {
    }
    public void setDescription(String desc) {
        this.desc = desc;
    }
    public void setTaskType(TaskType t) {
        this.t = t;
    }
    public String getDescription() {
        return this.desc;
    }
    public TaskType getTaskType() {
        return this.t;
    }

    /**
     * Executes the specific action associated with this command type.
     *
     * @param tasks List of tasks to perform actions on.
     * @param ui User interface for display messages and updates.
     * @param storage Storage object responsible for saving and loading date.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if this command is an exit command.
     */
    public abstract boolean isExit();
}
