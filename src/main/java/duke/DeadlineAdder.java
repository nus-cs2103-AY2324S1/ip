package duke;

public class DeadlineAdder implements Executable {
    private String name;
    private String endTime;
    private TaskList tasks;

    public DeadlineAdder(TaskList tasks, String name, String endTime) {
        this.name = name;
        this.endTime = endTime;
        this.tasks = tasks;
    }
    @Override
    public String execute() {
        Task task = new Deadline(name, endTime);
        tasks.add(task);
        return String.format("Got it! I've added this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.",
                task.toString(),
                tasks.size());
    }
}
