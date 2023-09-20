package duke.commands;

import java.io.IOException;
import java.util.Date;

import duke.data.Message;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Event;
import duke.storage.Storage;

/**
 * Represents a command for adding an event task to TaskList,
 * writing it to a file, and displaying a task added message.
 */
public class AddEventCommand extends Command {
    /** Description of the event */
    private final String description;
    /** Start date of event */
    private final Date from;

    /** End date of event */
    private final Date to;

    /**
     * Constructor to initialize AddEventCommand.
     *
     * @param description Description of the event.
     * @param from        Start date of the event.
     * @param to          End date of the event.
     */
    public AddEventCommand(String description, Date from, Date to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList taskList, Message message, Storage storage) throws DukeException, IOException {
        Event newEvent = new Event(description, from, to);
        if (taskList.checkClash(newEvent)) {
            throw new DukeException("Error! Schedule Clashed!");
        }
        taskList.addTask(newEvent);
        Storage.save(newEvent);
        assert taskList.countTasks() >= 0 : "Invalid task list size";
        return message.showTaskAdded(newEvent, taskList.countTasks());
    }
}
