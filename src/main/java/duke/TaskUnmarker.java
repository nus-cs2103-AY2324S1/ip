package duke;

public class TaskUnmarker implements Executable {
    private int index;
    private TaskList tasks;

    public TaskUnmarker(TaskList tasks, int index) {
        this.index = index;
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        tasks.unmark(index);
        Task task = tasks.get(index);
        return "OK, I've marked this task as not done yet:\n" +
            task.toString();
    }
}
