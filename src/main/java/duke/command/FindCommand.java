package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * Command class to find tasks.
 */
public class FindCommand extends Command {
    private String description;

    /**
     * Constraucts the find command object with description to find.
     *
     * @param description
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command. Finds tasks according to the description given.
     *
     * @param tasks   The list of tasks stored in TaskList object.
     * @param ui      The Ui object to interact with user.
     * @param storage The object used to store the tasks in case of changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.description == null) {
            throw new DukeException("Description cannot be empty!");
        }
        ui.display("    Here are the matching tasks in your list:");
        ui.display(tasks.find(this.description));
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand o = (FindCommand) other;
        return this.description.equals(o.description);
    }
}
