package duke;

/**
 * Adds the event into the DukeList.
 */
public class EventAdder extends Command {

    //The name of the event
    private String taskName;

    //The start date of the event.
    private String startDate;

    //The end date of the event
    private String endDate;

    //The list for which the task is added.
    private TaskList list;


    /**
     * Instantiates an instance of an EventAdder.
     * @param taskName The name of the task
     * @param startDate The start date of the task.
     * @param endDate The end date of the task.
     * @param list The list to be added to the task.
     */
    public EventAdder(String taskName, String startDate, String endDate, TaskList list) {
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.list = list;
    }

    /**
     * Adds the event into the DukeList
     * @return The message confirming the addition of the Event.
     */
    @Override
    public String execute() {
        Task event = new Event(taskName, startDate, endDate);
        return list.store(event);
    }
}
