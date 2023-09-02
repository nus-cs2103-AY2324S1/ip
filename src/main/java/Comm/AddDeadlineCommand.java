package Comm;
import TaskManager.Deadlines;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;

/**
 * Command to add a Deadlines task.
 */
public class AddDeadlineCommand extends Command{
    private String taskDesc;
    private String dueDateStr;

    /**
     * Constructs an `AddDeadlineCommand` object with the specified user input and due date string.
     *
     * @param taskDesc  The task description.
     * @param dueDateStr The due date and time in string.
     */
    public AddDeadlineCommand(String taskDesc, String dueDateStr) {
        this.dueDateStr = dueDateStr;
        this.taskDesc = taskDesc;
    }

    /**
     * Executes the command to add a Deadlines task to the task list, update the storage, and notify the user interface.
     *
     * @param task The task list to which the Deadlines task will be added.
     * @param ui   The user interface.
     * @param f    The file handler for storing tasks.
     */
    @Override
    public void execute(TaskList task, Ui ui, FileHandler f) {
        Deadlines newdeadlines = new Deadlines(taskDesc, dueDateStr);
        if (newdeadlines.isValid()) {
            task.add(newdeadlines);
            FileHandler.writeTasksToFile(task);
            ui.addedDeadlines(newdeadlines);
        }
    }

    /**
     * Check whether the command is an exit command.
     *
     * @return `false` because this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}