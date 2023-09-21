package duke.command;

import duke.main.Storage;
import duke.task.Task;
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
     * The task to be marked.
     */
    private Task markedTask;

    /**
     * The constructor for MarkCommand
     * @param index The index of the task in the taskList to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, CommandList commandList, boolean write) {
        this.markedTask = taskList.getTask(this.index - 1);
        if (this.markedTask.isDoneGetter()) {
            return "JonBird:\n\tYou have already marked this task as done:"
                    + "\n\t\t" + taskList.getTask(this.index - 1).printTask();
        }
        taskList.getTask(this.index - 1).markAsDone();
        if (write) {
            commandList.addCommand(this);
            storage.writeData(taskList.convertToFileContent());
            storage.previousCommandsWriter(commandList.convertToFileContent());
        }
        return this.printCommand(taskList);
    }

    @Override
    public String printCommand(TaskList taskList) {
        return "JonBird:\n\tNice! I've marked this task as done:"
                + "\n\t\t" + taskList.getTask(this.index - 1).printTask();
    }

    /**
     * Returns the task that is marked by the command
     */
    public Task getTask() {
        return this.markedTask;
    }

    /**
     * Returns the index of the task that is marked by the command
     */
    public int getIndex() {
        return this.index;
    }
}
