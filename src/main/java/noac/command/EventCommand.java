package noac.command;

import noac.Storage;
import noac.TaskList;
import noac.Ui;
import noac.task.Event;

import java.time.LocalDateTime;

/**
 * For executing the event command.
 */
public class EventCommand extends Command {

    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Create the event command with the required variables.
     *
     * @param description description of the event.
     * @param from when the event starts
     * @param to when the event ends
     */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Create a Event, store it in Tasklist and save it.
     *
     * @param tasks List of all the task.
     * @param ui UI for printing result to user.
     * @param storage Storage class meant for saving to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        Event e = new Event(this.description, this.from, this.to);

        tasks.addTask(e);

        ui.showAddTask(e, tasks.size());

        storage.save(tasks);

    }
}
