package spot.command;

import java.time.LocalDate;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;
import spot.task.Event;

/**
 * Represents a command to add a new event.
 */
public class AddEventCommand extends Command {
    private String description;
    private LocalDate start;
    private LocalDate end;

    /**
     * Constructs a new AddEventCommand object.
     *
     * @param description Description of the event.
     * @param start Start date of the event.
     * @param end End date of the event.
     */
    public AddEventCommand(String description, LocalDate start, LocalDate end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the AddEventCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui object.
     * @param storage Current Storage object.
     * @throws SpotException If there are any errors when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        Event e = tasks.addEvent(description, start, end);
        ui.setAdd(tasks, e);
    }

    /**
     * Checks if the AddEventCommand is an ExitCommand.
     *
     * @return Boolean representing whether the AddEventCommand is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
