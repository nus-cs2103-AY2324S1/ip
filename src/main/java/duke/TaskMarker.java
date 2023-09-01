package duke;

public class TaskMarker implements Executable {
    private int index;
    private TaskList tasks;

    public TaskMarker(TaskList tasks, int index) {
        this.index = index;
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        tasks.mark(index);
        Task task = tasks.get(index);
        return "Nice! I've marked this task as done:\n"
            + task.toString();
    }
}
