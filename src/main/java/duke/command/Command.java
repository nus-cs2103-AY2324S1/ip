package duke.command;

import duke.DukeException;
import duke.management.NotesList;
import duke.management.Storage;
import duke.management.TaskList;

/**
 * Command Class.
 */
public abstract class Command {
    protected String command;

    /**
     * Command Constructor.
     *
     * @param command Command from user's input.
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Executes the command.
     *
     * @param storage Storage.
     * @param tasks TaskList.
     * @param notes NotesList.
     * @return Response to be given to the user.
     */
    public abstract String execute(Storage storage, TaskList tasks, NotesList notes);

    /**
     * Validates the command if not, throws a duke exception.
     *
     * @throws DukeException Throws Empty or Non-Empty Duke Exception
     */
    public abstract void validateCommand() throws DukeException;
}
