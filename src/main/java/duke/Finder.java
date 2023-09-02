package duke;

/**
 * Represents a task finder that can filter and retrieve tasks based on a regular expression.
 */
public class Finder implements Executable {
    private String regex;
    private TaskList tasks;

    /**
     * Constructs a Finder with the specified regular expression and TaskList.
     *
     * @param tasks The TaskList containing the tasks to filter.
     * @param regex The regular expression used for filtering tasks.
     */
    public Finder(TaskList tasks, String regex) {
        this.regex = regex;
        this.tasks = tasks;
    }

    /**
     * Executes the task finder and returns a string representation of filtered tasks.
     *
     * @return A string representing the tasks that match the provided regular expression.
     */
    @Override
    public String execute() {
        return tasks.filteredToString(regex);
    }
}
