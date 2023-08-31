package brotherman.commands;

import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;

/**
 * Represents a command to mark a task as done
 */
public class MarkDoneCommand extends Command {
    /**
     * Task number to be marked as done
     */
    private int taskNum;

    /**
     * Constructor for MarkDoneCommand
     * @param taskNum Task number to be marked as done
     */
    public MarkDoneCommand(int taskNum) {
        super(false);
        this.taskNum = taskNum;
    }

    /**
     * Executes the command to mark a task as done
     * @param tasks Task list to be marked as done
     * @param ui Ui to show the user the task has been marked as done
     * @param storage Storage to save the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markDone(taskNum);
    }
}
