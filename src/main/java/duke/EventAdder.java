package duke;

/**
 * Represents an executable task for adding an event.
 */
public class EventAdder implements Executable {
    private String name;
    private String startTime;
    private String endTime;
    private TaskList tasks;

    /**
     * Constructs an EventAdder instance.
     *
     * @param tasks     The task list to add the event to.
     * @param name      The name of the event.
     * @param startTime The start time of the event.
     * @param endTime   The end time of the event.
     */
    public EventAdder(TaskList tasks, String name, String startTime, String endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tasks = tasks;
    }

    /**
     * Executes the task of adding an event.
     *
     * @return A response message indicating the task has been added and the updated number of tasks.
     */
    @Override
    public String execute() {
        Task task = new Event(name, startTime, endTime);
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
