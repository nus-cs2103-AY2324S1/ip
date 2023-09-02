package duke;

/**
 * Represents an executable command for adding a new ToDo task to the task list.
 */
public class ToDoAdder implements Executable {
    private String name;
    private TaskList tasks;

    /**
     * Constructs a ToDoAdder with the specified task list and task name.
     *
     * @param tasks The task list in which the task will be added.
     * @param name  The name of the ToDo task to be added.
     */
    public ToDoAdder(TaskList tasks, String name) {
        this.name = name;
        this.tasks = tasks;
    }

    /**
     * Adds a new ToDo task to the task list and returns a confirmation message.
     *
     * @return A message confirming the task has been added to the list.
     */
    @Override
    public String execute() {
        Task task = new ToDo(name);
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
