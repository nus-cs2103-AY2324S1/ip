package monke.commands;

import monke.MonkeException;
import monke.Storage;
import monke.TaskList;
import monke.Ui;
import monke.tasks.Event;

/**
 * The EventCommand class represents a command to add an event task to the task list.
 * It extends the Command class.
 */
public class EventCommand extends Command {
    /** The command word for parser to recognize this command. */
    public static final String COMMAND_WORD = "event";

    /** The description of the event task. */
    private String description;

    /** The start time of the event. */
    private String start;

    /** The end time of the event. */
    private String end;

    /**
     * Constructs an EventCommand with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public EventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the EventCommand to add an event task to the task list.
     *
     * @param ui      The user interface.
     * @param storage The storage to read and write data.
     * @param tasks   The list of tasks.
     * @throws MonkeException If data cannot be saved in the storage.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException {
        Event event = new Event(this.description, this.start, this.end);
        tasks.add(event);
        storage.saveData(tasks);

        ui.showAddTask(event, tasks.size());
    }
}
