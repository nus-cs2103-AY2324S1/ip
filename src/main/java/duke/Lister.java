package duke;

/**
 * Represents a task lister that can retrieve and display all tasks in a TaskList.
 */
public class Lister implements Executable {
    private TaskList tasks;

    /**
     * Constructs a Lister with the specified TaskList.
     *
     * @param tasks The TaskList containing the tasks to list.
     */
    public Lister(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Executes the task lister and returns a string representation of all tasks.
     *
     * @return A string representing all tasks in the TaskList.
     */
    @Override
    public String execute() {
        return tasks.toString();
    }
}
