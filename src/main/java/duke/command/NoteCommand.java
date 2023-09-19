package duke.command;

import duke.DukeException;
import duke.management.NotesList;
import duke.management.Storage;
import duke.management.TaskList;
import duke.note.Note;

/**
 * Note command class.
 */
public class NoteCommand extends Command {
    /**
     * Note Command Constructor.
     *
     * @param command Note Command input from user.
     */
    public NoteCommand(String command) {
        super(command);
    }

    @Override
    public String execute(Storage storage, TaskList tasks, NotesList notes) {
        String[] commandArr = this.command.split(" ", 2);
        Note note = new Note(commandArr[1]);
        notes.addNote(note);
        storage.saveNotesToFile(notes.getNotes());
        return "Ren helped you add a new note: \n" + note.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateCommand() throws DukeException {
        String[] commandArr = this.command.split(" ", 2);
        if (commandArr.length < 2 || commandArr[1].isEmpty()) {
            throw new DukeException(String.format(DukeException.EMPTY_DESCRIPTION, "note"));
        }
    }
}
