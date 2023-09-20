package duke.command;

import duke.main.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A class for the command for marking a task in list as undone
 * based on user input.
 */
public class UnmarkCommand extends Command {

    /**
     * The index of the task in the taskList to be unmarked.
     */
    private final int index;

    /**
     * The task to be unmarked.
     */
    private Task unmarkedTask;

    /**
     * The constructor for UnmarkCommand
     * @param index The index of the task in the taskList to be unmarked
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, CommandList commandList, boolean write) {
        this.unmarkedTask = taskList.getTask(this.index - 1);
        if (!this.unmarkedTask.isDoneGetter()) {
            return "JonBird:\n\tYou have already marked this task as undone:"
                    + "\n\t\t" + taskList.getTask(this.index - 1).printTask();
        }
        taskList.getTask(this.index - 1).markAsUndone();
        if (write) {
            commandList.addCommand(this);
            storage.writeData(taskList.convertToFileContent());
            storage.previousCommandsWriter(commandList.convertToFileContent());
        }
        return this.printCommand(taskList);
    }

    @Override
    public String printCommand(TaskList taskList) {
        return "JonBird:\n\tOK, I've marked this task as not done yet:"
                + "\n\t\t" + taskList.getTask(this.index - 1).printTask();
    }

    @Override
    public boolean isContinue() {
        return true;
    }

    /**
     * Returns the task that is unmarked by the command
     */
    public Task getTask() {
        return this.unmarkedTask;
    }

    /**
     * Returns the index of the task that is unmarked by the command
     */
    public int getIndex() {
        return this.index;
    }
}
