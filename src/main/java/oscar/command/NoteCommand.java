package oscar.command;

import oscar.essential.InfoList;
import oscar.essential.Storage;
import oscar.exception.OscarException;
import oscar.info.Note;

/**
 * Command to create a new note.
 */
public class NoteCommand extends Command {
    private final String details;

    /**
     * Instantiates a note command.
     *
     * @param d Description of note.
     */
    public NoteCommand(String d) {
        this.details = d;
    }

    /**
     * Creates a new note and save it to the info list.
     *
     * @param infos   ArrayList of infos.
     * @param storage File loading and saving handler.
     * @return String output of note.
     * @throws OscarException Incorrect format of note.
     */
    @Override
    public String execute(InfoList infos, Storage storage) throws OscarException {
        assert infos != null;
        assert storage != null;
        validate();
        Note newNote = new Note(details);
        infos.add(newNote);
        storage.save(infos);
        infos.listCount();
        return "Oscar has added:\n" + newNote + "\n";
    }

    /**
     * Validates description of note.
     * Format: note [description].
     *
     * @throws OscarException Description is missing or not within 200 characters.
     */
    public void validate() throws OscarException {
        if (details.isEmpty()) {
            throw new OscarException("Sorry! The description of a note cannot be empty.\n");
        } else if (details.length() > 200) {
            throw new OscarException("Sorry! The description of a note cannot exceed 200 characters.\n");
        }
    }
}
