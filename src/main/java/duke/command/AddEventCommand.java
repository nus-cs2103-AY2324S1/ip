package duke.command;

import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.data.task.TaskList;
import duke.ui.Ui;
import duke.data.task.Event;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a new event to the list of tasks.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    private final Event toAdd;

    public AddEventCommand(String description, String from, String to) throws DukeException {
        try {
            this.toAdd = new Event(description, LocalDate.parse(from), LocalDate.parse(to));
        } catch (DateTimeParseException e) {
            throw new DukeException("The dates must be filled in \"yyyy-mm-dd\" format.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(toAdd);
        ui.showMessage(
                "Got it. I've added this task:",
                "\t" + toAdd,
                "Now you have " + tasks.size() + " tasks in the list."
        );
    }
}
