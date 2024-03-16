package duke.command;

import duke.error.DukeException;
import duke.lib.Storage;
import duke.lib.UI;
import duke.task.TaskList;

/**
 * Represents an abstract command that can be executed by the Duke chatbot.
 */
public abstract class Command {
    protected String usageText;

    /**
     * Constructs a Command object with the specified usage text.
     *
     * @param usageText The usage text for the command.
     */
    protected Command(String usageText) {
        this.usageText = usageText;
    }

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws DukeException If there's an issue executing the command.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("Usage: %s", this.usageText);
    }

}
