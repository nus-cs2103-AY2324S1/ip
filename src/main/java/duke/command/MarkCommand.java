package duke.command;

import duke.main.Storage;
import duke.task.TaskList;

/**
 * A class for the command for marking a task in list as done
 * based on user input.
 */
public class MarkCommand extends Command {

    /**
     * The index of the task in the taskList to be marked.
     */
    private final int index;

    /**
     * The constructor for MarkCommand
     * @param index The index of the task in the taskList to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        taskList.getTask(this.index - 1).markAsDone();
        storage.writeData(taskList.convertToFileContent());
        return this.printCommand(taskList);
    }

    @Override
    public String printCommand(TaskList taskList) {
        return "JonBird:\n\tNice! I've marked this task as done:"
                + "\n\t\t" + taskList.getTask(this.index - 1).printTask();
    }

    @Override
    public boolean isContinue() {
        return true;
    }
}
