package duke.notes;

import duke.Duke;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.MissingTaskException;
import duke.tasks.Task;
import duke.utils.Commands;
import duke.utils.Parser;

import java.util.ArrayList;
import java.util.List;

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

    public static Note createNote(String input) throws DukeException {
        String noteTitle = Parser.obtainTitle(input, Commands.NOTE);
        return new Note(noteTitle);
    }

    public boolean isEmpty() {
        return notes.isEmpty();
    }

    /**
     * Adds a note to the list of notes.
     * @param note The note to be added.
     */
    public void addNote(Note note) {
        notes.add(note);
    }

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
