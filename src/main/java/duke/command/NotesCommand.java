package duke.command;

import duke.DukeException;
import duke.management.NotesList;
import duke.management.Storage;
import duke.management.TaskList;

/**
 * Notes Command Class.
 */
public class NotesCommand extends Command {
    /**
     * Constructor for Notes Command.
     *
     * @param commmand User command.
     */
    public NotesCommand(String commmand) {
        super(commmand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Storage storage, TaskList tasks, NotesList notes) {
        return notes.printNotes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateCommand() throws DukeException {
        String[] commandArr = this.command.split(" ", 2);
        if (commandArr.length > 1) {
            throw new DukeException(DukeException.EMPTY);
        }
    }
}
