package commands;

import java.time.LocalDateTime;

import data.TaskList;
import data.exception.DukeException;
import data.tasks.Event;
import data.tasks.Task;
import storage.Storage;
import ui.Ui;

/**
 * The EventCommand class.
 * Handles creating a new {@link Event}.
 */
public class EventCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * The constructor method of the EventCommand class.
     * Takes in the description and the two dates of an {@link Event}.
     * 
     * @param description The description of an event.
     * @param from The start date of an event.
     * @param to The end date of an event.
     */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(
            TaskList tasks, Storage storage, Ui ui) throws DukeException {
        Task event = new Event(description, from, to);
        tasks.add(event);
        storage.update(tasks);
        ui.displayMsg(new String[] {
            "Okie! I've added a new " + Ui.cTxt("EVENT", Ui.Color.YELLOW) + ":",
            "  " + event.toString(),
            "Total no. of tasks stored: " + tasks.getSize()
        });
    }
}
