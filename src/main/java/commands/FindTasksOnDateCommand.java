package commands;

import java.util.function.Predicate;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to find tasks on a specific date in the task list.
 * This command filters the task list based on a given predicate to find tasks on the specified date.
 */
public class FindTasksOnDateCommand extends Command{
    /**
     * The predicate used to filter tasks by date.
     */
    private Predicate<Task> predicate;

    /**
     * The target date for finding tasks.
     */
    private String targetDate;

    /**
     * Initializes a new FindTasksOnDateCommand instance with the specified predicate and target date.
     *
     * @param predicate The predicate used to filter tasks by date.
     * @param targetDate The target date for finding tasks.
     */
    public FindTasksOnDateCommand(Predicate<Task> predicate, String targetDate) {
        super(false, CommandType.DATE);
        this.predicate = predicate;
        this.targetDate = targetDate;
    }

    /**
     * Executes the command by filtering the task list based on the given predicate to find tasks on the specified date.
     * It then displays the filtered tasks to the user or a message indicating that no tasks were found on the date.
     * 
     * @param list The task list to filter.
     * @param ui The user interface for displaying filtered tasks or messages.
     * @param storage The storage for saving and loading tasks (not used in this command).
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage<Task> storage) {
        TaskList tasksOnDate = list.filter(predicate);

        if (tasksOnDate.isEmpty()) {
            ui.showNoTaskOnDate(this.targetDate);;
        } else {
            ui.showTasksOnDate(this.targetDate, tasksOnDate.toString());
        }
    }
}
