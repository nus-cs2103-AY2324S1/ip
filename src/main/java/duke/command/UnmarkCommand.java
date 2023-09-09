package duke.command;

import duke.main.Storage;
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
     * The constructor for UnmarkCommand
     * @param index The index of the task in the taskList to be unmarked
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        taskList.getTask(this.index - 1).markAsUndone();
        storage.writeData(taskList.convertToFileContent());
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
}
