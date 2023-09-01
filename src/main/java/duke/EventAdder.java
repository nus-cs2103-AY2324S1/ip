package duke;

public class EventAdder implements Executable {
    private String name;
    private String startTime;
    private String endTime;
    private TaskList tasks;

    public EventAdder(TaskList tasks, String name, String startTime, String endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        Task task = new Event(name, startTime, endTime);
        tasks.add(task);
        return String.format("Got it! I've added this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.",
                task.toString(),
                tasks.size());
    }
}
