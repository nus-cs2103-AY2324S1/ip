package ekud.ui;

import java.util.List;

import ekud.command.Command;
import ekud.error.EkudException;
import ekud.state.Task;
import ekud.state.TaskList;
import ekud.util.Pair;

/**
 * Represents any user interface to ekud.
 * This could be in the form of a command-line interface or a graphical user
 * interface.
 */
public abstract class Ui {
    /**
     * Reads a command from the user interface.
     * 
     * @return Returns a pair containing the command that was read and whether to
     *         continue the program i.e. EOF not passed.
     */
    public abstract Pair<Command, Boolean> readCommand();

    /**
     * Display a greeting to the user.
     */
    public abstract void showGreeting();

    /**
     * Display a farewell message to the user.
     */
    public abstract void showFarewell();

    /**
     * Display the given task list to the user.
     * 
     * @param taskList The task list.
     */
    public abstract void showTaskList(TaskList taskList);

    /**
     * Display the number of tasks in the given task list to the user.
     * 
     * @param taskList The task list.
     */
    public abstract void showTaskCount(TaskList taskList);

    /**
     * Display the results of adding the given task to the user.
     * 
     * @param task The added task.
     */
    public abstract void showTaskAdded(Task task);

    /**
     * Display the results of removing the given task to the user.
     * 
     * @param task The deleted task.
     */
    public abstract void showTaskRemoved(Task task);

    /**
     * Display the results of marking the given task to the user.
     * 
     * @param task The marked task.
     */
    public abstract void showTaskMarked(Task task);

    /**
     * Display the results of unmarking the given task to the user.
     * 
     * @param task The unmarked task.
     */
    public abstract void showTaskUnmarked(Task task);

    /**
     * Display a list of tasks that matched the find query.
     * 
     * @param foundTasks The tasks that matched the find query.
     */
    public abstract void showFoundTasks(List<Task> foundTasks);

    /**
     * Display an error that occured to the user.
     * 
     * @param error The error that occured.
     */
    public abstract void showError(EkudException error);
}
