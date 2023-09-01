package corgi.commands;

import java.time.LocalDate;
import java.util.function.Predicate;

import corgi.storage.Storage;
import corgi.tasks.Deadline;
import corgi.tasks.Event;
import corgi.tasks.Task;
import corgi.tasks.TaskList;

import corgi.ui.Ui;

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
    private final LocalDate target;

    /**
     * Initializes a new FindTasksOnDateCommand instance with the target date.
     *
     * @param target The target date
     */
    public FindTasksOnDateCommand(LocalDate target) {
        super(false, CommandType.DATE);
        this.target = target;

        this.predicate = t -> {
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                return d.isHappeningOnDate(this.target);
            } else if (t instanceof Event) {
                Event e = (Event) t;
                return e.isHappeningOnDate(this.target);
            }
            return false;
        };
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

        String outputDate = this.target.format(Task.DATE_OUTPUT_FORMATTER);

        if (tasksOnDate.isEmpty()) {
            ui.showNoTaskOnDate(outputDate);;
        } else {
            ui.showTasksOnDate(outputDate, tasksOnDate.toString());
        }
    }
}
