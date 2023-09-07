package duke.commands;

import java.io.IOException;
import java.util.Date;

import duke.data.TaskList;
import duke.data.task.Event;
import duke.storage.Storage;
import duke.data.Message;

/**
 * The AddEventCommand adds an event into TaskList, writes into .txt file
 * and display a task added message when it is executed.
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
     * @param from Start date of event.
     * @param to End date of event.
     */
    public AddEventCommand(String description, Date from, Date to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList taskList, Message message, Storage storage) throws IOException {
        Event newEvent = new Event(description, from, to);
        taskList.addTask(newEvent);
        Storage.save(newEvent);
        return message.showTaskAdded(newEvent, taskList.countTasks());
    }
}
