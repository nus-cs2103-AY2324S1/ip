package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * A command class to find tasks at certain location.
 */
public class LocationCommand extends Command {
    private String location;

    /**
     * Constructs a location command to find tasks matching location.
     *
     * @param location
     */
    public LocationCommand(String location) {
        this.location = location;
    }

    /**
     * Executes the command. Displays the location of a task or update its location.
     *
     * @param tasks   The list of tasks stored in TaskList object.
     * @param ui      The Ui object to interact with user.
     * @param storage The object used to store the tasks in case of changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.location == null) {
            throw new DukeException("Location cannot be empty!");
        }
        ui.display("    Here are the matching tasks in your list:");
        ui.display(tasks.findByLocation(this.location));
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof LocationCommand)) {
            return false;
        }

        LocationCommand o = (LocationCommand) other;
        return this.location.equals(o.location);
    }
}
