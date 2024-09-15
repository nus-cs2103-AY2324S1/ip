package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * A command to exit the chatbot.
 */
public class ExitCommand extends Command {

    public ExitCommand() {

    }

    /**
     * Ends the chatbot with a goodbye message.
     *
     * @param tasks   The list of tasks stored in TaskList object.
     * @param ui      The Ui object to interact with user.
     * @param storage The object used to store the tasks in case of changes
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.goodBye();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof EventCommand)) {
            return false;
        }

        return true;
    }
}
