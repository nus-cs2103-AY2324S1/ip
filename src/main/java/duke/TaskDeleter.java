package duke;

public class TaskDeleter implements Executable {
    private int index;
    private TaskList tasks;

    public TaskDeleter(TaskList tasks, int index) {
        this.index = index;
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        Task task = tasks.get(index);
        tasks.delete(index);
        return String.format("Noted. I've removed this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.",
                task.toString(),
                tasks.size());
    }
}
