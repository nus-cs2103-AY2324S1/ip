package duke.notes;

import java.util.ArrayList;
import java.util.List;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.MissingTaskException;
import duke.utils.Commands;
import duke.utils.Parser;

/** Class that contains the list of notes when the chatbot is active. */
public class NoteList {

    private List<Note> notes;

    public NoteList(List<String> stringNotes) {
        this.notes = stringToNote(stringNotes);
    }

    private static List<Note> stringToNote(List<String> stringNotes) {
        List<Note> output = new ArrayList<>();
        try {
            for (int i = 0; i < stringNotes.size(); i++) {
                String nextNote = stringNotes.get(i);
                Note note = createNote(nextNote);
                output.add(note);
            }
            return output;
        } catch (DukeException e) {
            return output;
        }
    }

    /**
     * Creates a note based on the description given by the user.
     *
     * @param input Description of the note.
     * @return The note created.
     * @throws DukeException If description for note does not exist.
     */
    public static Note createNote(String input) throws DukeException {
        String noteTitle = Parser.obtainTitle(input, Commands.NOTE);
        return new Note(noteTitle);
    }

    public boolean isEmpty() {
        return notes.isEmpty();
    }

    /**
     * Adds a note to the list of notes.
     *
     * @param note The note to be added.
     */
    public void addNote(Note note) {
        notes.add(note);
    }

    /**
     * Returns a list of note descriptions.
     *
     * @param typeOfDes The type of description to be returned.
     * @return The list of descriptions.
     */
    public List<String> getNotesDes(int typeOfDes) {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < notes.size(); i++) {
            if (typeOfDes == 0) {
                output.add(notes.get(i).toString());
            } else if (typeOfDes == 1) {
                output.add(notes.get(i).getNote());
            }
        }
        return output;
    }

    /**
     * Deletes a note and returns the status of the deleted task.
     *
     * @param input The index of note to be deleted.
     * @return The status of the deleted task.
     * @throws DukeException if input cannot be recognised.
     */
    public String deleteNote(String input) throws DukeException {
        try {
            int taskNum = Integer.valueOf(input.split(" ")[1]);
            Note deleted = notes.remove(taskNum - 1);
            return "Note: '" + deleted.getNote() + "'" + " has been deleted!";

        } catch (IndexOutOfBoundsException ioob) {
            throw new MissingTaskException("Missing Task");
        } catch (Exception e) {
            throw new InvalidInputException("Invalid input");
        }
    }
}
