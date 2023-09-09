package duke.command;

import duke.main.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A class for the command for deleting task from list based on user input.
 */
public class DeleteCommand extends Command {

    /**
     * The index of the task in the taskList to be deleted.
     */
    private final int index;

    /**
     * The constructor for DeleteCommand
     * @param index The index of the task in the taskList to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        TaskList temp = taskList;
        taskList.removeTask(this.index - 1);
        storage.writeData(taskList.convertToFileContent());
        return this.printCommand(temp);
    }

    @Override
    public String printCommand(TaskList taskList) {
        Task temp = taskList.getTask(this.index - 1);
        return "JonBird:\n\tNoted. I've removed this task:\n"
                + "\t\t" + temp.printTask()
                + "\n\tNow you have " + (taskList.size() - 1) + " tasks in the list.";
    }

    @Override
    public boolean isContinue() {
        return true;
    }
}
