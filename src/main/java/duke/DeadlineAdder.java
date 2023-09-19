package duke;

/**
 * Represents a class for adding a deadline task to the task list.
 * This class implements the Executable interface to define the behavior
 * of adding a deadline task when executed.
 */
public class DeadlineAdder implements Executable {
    private String name;
    private String endTime;
    private TaskList tasks;

    /**
     * Constructs a DeadlineAdder with the specified task list, task name, and end time.
     *
     * @param tasks   The task list to which the deadline task will be added.
     * @param name    The name of the deadline task.
     * @param endTime The end time of the deadline task.
     */
    public DeadlineAdder(TaskList tasks, String name, String endTime) {
        this.name = name;
        this.endTime = endTime;
        this.tasks = tasks;
    }

    /**
     * Executes the DeadlineAdder to add a deadline task to the task list.
     *
     * @return A message indicating the successful addition of the task and the updated task count.
     */
    @Override
    public String execute() {
        Task task = new Deadline(name, endTime);
        tasks.add(task);
        return String.format("Got it! I've added this task:\n"
                +
                "%s\n"
                +
                "Now you have %d tasks in the list.",
                task.toString(),
                tasks.size());
    }
}
