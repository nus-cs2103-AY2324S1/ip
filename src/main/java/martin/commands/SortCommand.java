package martin.commands;

import martin.exceptions.MartinException;
import martin.task.Deadline;
import martin.task.Event;
import martin.task.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents a command that sorts the tasks in the task list.
 */
public class SortCommand implements Command {

    private ArrayList<Task> tasks;

    /**
     * Creates a SortCommand.
     *
     * @param tasks The list of tasks.
     */
    public SortCommand(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the LocalDateTime associated with the task.
     * 
     * @param task The task to inspect.
     * @return The LocalDateTime associated with the task.
     */
    private LocalDateTime getDateFromTask(Task task) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            return ((Event) task).getFrom();
        }
        return LocalDateTime.MAX; // Default, for tasks without a date
    }

    /**
     * Sorts the tasks by date and time.
     *
     * @return A confirmation message indicating that the tasks have been sorted.
     * @throws MartinException If there's an error executing the command.
     */
    @Override
    public String execute() throws MartinException {
        if (tasks.isEmpty()) {
            return "☹ OOPS!!! The task list is empty.";
        }

        Comparator<Task> taskComparator = Comparator.comparing(this::getDateFromTask);
        Collections.sort(tasks, taskComparator);

        return "Tasks have been sorted!";
    }
}
