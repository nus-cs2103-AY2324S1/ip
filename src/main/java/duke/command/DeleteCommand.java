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
    private int index;

    /**
     * The constructor for DeleteCommand
     * @param index The index of the task in the taskList to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        this.printCommand(taskList);
        taskList.removeTask(this.index - 1);
        storage.writeData(taskList.convertToFileContent());
    }

    @Override
    public void printCommand(TaskList taskList) {
        Task temp = taskList.getTask(this.index - 1);
        System.out.println("JonBird:\n\tNoted. I've removed this task:");
        System.out.println("\t\t" + temp.printTask());
        System.out.println("\tNow you have " + (taskList.size() - 1) + " tasks in the list.");
    }

    @Override
    public boolean isContinue() {
        return true;
    }
}
