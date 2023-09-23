package corgi.commands;

import java.time.LocalDate;
import java.util.Stack;
import java.util.function.Predicate;

import corgi.State;
import corgi.tasks.Deadline;
import corgi.tasks.Event;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.ui.TextRenderer;
import javafx.util.Pair;

/**
 * Represents a command to find tasks on a specific date in the task list.
 * This command filters the task list based on a given predicate to find tasks on the specified date.
 */
public class FindTasksOnDateCommand extends Command {
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
        super(false);
        this.target = target;

        // Define a predicate to filter tasks based on whether they are happening on the target date.
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
     * It then returns the filtered tasks to the user or a message indicating that no tasks were found on the date.
     *
     * @param currState The current state of the application.
     * @param history The history stack to store the states.
     * @return A pair containing the new state and a string message indicating the result of the command execution.
     */
    @Override
    public Pair<State, String> execute(State currState, Stack<Pair<State, Command>> history) {
        TaskList currList = currState.getTaskList();
        TextRenderer currTextRenderer = currState.getTextRenderer();

        TaskList tasksOnDate = currList.filter(predicate);

        String outputDate = this.target.format(Task.DATE_OUTPUT_FORMATTER);

        String returnMsg = tasksOnDate.isEmpty()
                ? currTextRenderer.showNoTaskOnDate(outputDate)
                : currTextRenderer.showTasksOnDate(outputDate, tasksOnDate.toString());

        return new Pair<>(currState, returnMsg);
    }
}
