package duke.command;

import duke.DukeException;
import duke.management.NotesList;
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
    public String execute(TaskList tasks, NotesList notes) {
        String[] commandArr = this.command.split(" ", 2);
        Note note = new Note(commandArr[1]);
        notes.addNote(note);

        return "Ren helped you add a new note: \n" + note.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateCommand() throws DukeException {
        String[] commandArr = this.command.split(" ", 2);
        if (commandArr.length < 2) {
            throw new DukeException(String.format(DukeException.NON_EMPTY, "note"));
        }
    }
}
