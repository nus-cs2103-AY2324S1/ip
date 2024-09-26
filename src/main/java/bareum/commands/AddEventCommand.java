package bareum.commands;

import bareum.EventTask;
import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

/**
 * This class implements the command for creating a new event.
 */
public class AddEventCommand extends Command {
    /**
     * Description of the event.
     */
    private String description;
    /**
     * Start date and time of the event.
     */
    private String startDateTime;
    /**
     * End date and time of the event.
     */
    private String endDateTime;

    /**
     * Create a new instance of a command that creates an event with the corresponding details when executed.
     * @param description Description of the event.
     * @param startDateTime Start date and time of the event.
     * @param endDateTime End date and time of the event.
     */
    public AddEventCommand(String description, String startDateTime,
                           String endDateTime) {
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Create a new event with the corresponding description and start and end date and time.
     *
     * @param ui Lets the user know if the creation of the event was successful.
     * @param storage Saves the event to the hard disk after creating it.
     * @param taskList Task list to add the event to.
     * @return Response to user input.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        EventTask task = EventTask.makeEvent(this.description, this.startDateTime, this.endDateTime);
        taskList.addTask(task);
        storage.saveNewTask(task);

        String added = "I have added this task:\n" + task + "\nYou now have "
                + taskList.size() + " task(s) in your list.";
        return added;
    }
}
