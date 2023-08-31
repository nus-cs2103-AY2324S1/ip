package duke.commands;

import java.io.IOException;
import java.util.Date;

import duke.data.task.Event;
import duke.data.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Event newEvent = new Event(description, from, to);
        taskList.addTask(newEvent);
        Storage.save(newEvent);
        ui.showTaskAdded(newEvent, taskList.countTasks());
    }
}
