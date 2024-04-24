package ekud.ui;

import java.util.List;

import ekud.command.Command;
import ekud.error.EkudException;
import ekud.state.Task;
import ekud.state.TaskList;

/**
 * Represents any user interface to ekud.
 * This could be in the form of a command-line interface or a graphical user
 * interface.
 */
public abstract class Ui {
    private CommandHandler handler;

    public void setOnCommand(CommandHandler handler) {
        this.handler = handler;
    }

    protected boolean handle(Command command) {
        if (handler == null) {
            return true;
        }
        return handler.handle(command);
    }

    public abstract void run();

    /**
     * Displays the given task list to the user.
     * 
     * @param taskList The task list.
     */
    public abstract void showTaskList(TaskList taskList);

    /**
     * Displays the number of tasks in the given task list to the user.
     * 
     * @param taskList The task list.
     */
    public abstract void showTaskCount(TaskList taskList);

    /**
     * Displays the results of adding the given task to the user.
     * 
     * @param task The added task.
     */
    public abstract void showTaskAdded(Task task);

    /**
     * Displays the results of removing the given task to the user.
     * 
     * @param task The deleted task.
     */
    public abstract void showTaskRemoved(Task task);

    /**
     * Displays the results of marking the given task to the user.
     * 
     * @param task The marked task.
     */
    public abstract void showTaskMarked(Task task);

    /**
     * Displays the results of unmarking the given task to the user.
     * 
     * @param task The unmarked task.
     */
    public abstract void showTaskUnmarked(Task task);

    /**
     * Displays a list of tasks that matched the find query.
     * 
     * @param foundTasks The tasks that matched the find query.
     */
    public abstract void showFoundTasks(List<Task> foundTasks);

    /**
     * Informsf the user that their task list has been cleaned.
     */
    public abstract void showTasksCleaned();

    /**
     * Displays an error that occured to the user.
     * 
     * @param error The error that occured.
     */
    public abstract void showError(EkudException error);
}
