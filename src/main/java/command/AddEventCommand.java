package command;

import duke.Ui;
import storage.FileHandler;
import storage.TaskList;
import taskmanager.Event;


/**
 * Command to add an Events task.
 */
public class AddEventCommand extends Command {
    private String taskDesc;
    private String to;
    private String from;

    /**
     * Constructs an `AddEventCommand` object with the specified user input and due date string.
     *
     * @param taskDesc The task description.
     * @param from      The start date and time in string.
     * @param to        The end date and time in string.
     * @throws IllegalArgumentException If either the task description, from date and to date is null.
     */
    public AddEventCommand(String taskDesc, String from, String to) {
        assert taskDesc != null : "taskDesc must not be null";
        assert from != null : "from must not be null";
        assert to != null : "to must not be null";

        this.taskDesc = taskDesc;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to add an Events task to the task list, update the storage, and notify the user interface.
     *
     * @param task The task list to which the Events task will be added.
     * @param ui   The user interface.
     * @param f    The file handler for storing tasks.
     *
     * @return     The string representation of the task.
     */
    @Override
    public String execute(TaskList task, Ui ui, FileHandler f) {
        Event newEvent = new Event(taskDesc, from, to);
        task.add(newEvent);
        FileHandler.writeTasksToFile(task);
        return "Help you added a new event.\n     " + newEvent.toString()
                + "\nNow you have " + task.size() + String.format(" task(s) in the list.");
    }
}
