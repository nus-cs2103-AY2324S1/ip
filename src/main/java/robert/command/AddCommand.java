package robert.command;

import java.time.LocalDate;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.Deadline;
import robert.task.Event;
import robert.task.Task;
import robert.task.TaskList;
import robert.task.ToDo;

/**
 * An Add extension of the <tt>Command</tt> class. Adds new task to
 * tasks given the task's necessary parameters such as description
 * and dates.
 *
 * @author Lee Zhan Peng
 */
public class AddCommand extends Command {

    /** The task to be added to tasks */
    private final Task task;

    /**
     * Constructs AddCommand using description. ToDo task is
     * initialised.
     *
     * @param description the description of the task.
     */
    public AddCommand(String description) {
        assert !description.isEmpty() : "Description must not be empty";

        this.task = new ToDo(description);
    }

    /**
     * Constructs AddCommand using description, a fromDate and a toDate. Event task is
     * initialised.
     *
     * @param description the description of the task.
     * @param fromDate the date where the task begins.
     * @param toDate the date where the task ends.
     * @throws RobertException if the toDate comes before the fromDate.
     */
    public AddCommand(String description, LocalDate fromDate, LocalDate toDate) throws RobertException {
        assert !description.isEmpty() : "Description must not be empty";

        this.task = new Event(description, fromDate, toDate);
    }

    /**
     * Constructs AddCommand using description and a byDate. Deadline task is
     * initialised.
     *
     * @param description the description of the task.
     * @param byDate the date where the task is due.
     */
    public AddCommand(String description, LocalDate byDate) {
        assert !description.isEmpty() : "Description must not be empty";

        this.task = new Deadline(description, byDate);
    }

    /**
     * Executes the addition of task to tasks.
     *
     * @param tasks the list of tasks to be added onto.
     * @param storage the storage that loads stored tasks from hard disk.
     * @return String of output message.
     * @throws RobertException as a mean of overriding the function.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RobertException {
        if (tasks.isInTaskList(this.task.getDescription())) {
            return "Hmm... You already have this task in your list.\n"
                    + "Check your list of tasks by using the 'list' command.";
        }

        tasks.addTask(this.task);
        return "Got it. I have added this task:\n  " + this.task
                + "\nNow you have " + tasks.getTaskCount() + " "
                + (tasks.getTaskCount() > 1 ? "tasks" : "task")
                + " in the list.";
    }

}
