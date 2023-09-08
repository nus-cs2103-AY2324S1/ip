package command;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to list all the current tasks.
 * This class extends the Command class and contains method to execute the list command
 * and to check whether it is an exit command.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command which triggers the UI to display all tasks currently in the task list.
     *
     * @param tasks The list of tasks to be displayed in the UI.
     * @param ui The UI where the task list will be displayed.
     * @param storage The storage of tasks, which is not utilized in this method but is here to override the abstract method in the parent class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    /**
     * Specifies that this command is not an exit command.
     *
     * @return false, as this command does not cause the program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
