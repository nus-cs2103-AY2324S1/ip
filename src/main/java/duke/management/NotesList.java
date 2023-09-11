package duke.management;

import java.util.ArrayList;

import duke.DukeException;
import duke.note.Note;
import duke.task.Task;

/**
 * Contains an Arraylist of all notes.
 */
public class NotesList {
    private final ArrayList<Note> notesList;

    public NotesList() {
        this.notesList = new ArrayList<>();
    }

    public NotesList(ArrayList<Note> loadedNotes) {
        this.notesList = loadedNotes;
    }

    /**
     * Adds a note to the list.
     *
     * @param note Note to be added.
     */
    public void addNote(Note note) {
        this.notesList.add(note);
    }

    /**
     * Returns the list of the notes;
     *
     * @return The list.
     */
    public ArrayList<Note> getNotes() {
        return this.notesList;
    }

    /**
     * Prints all the notes in the noteslist.
     */
    public String printNotes() {
        String response = "Here are the notes in your list: \n";
        for (int i = 0; i < notesList.size(); i++) {
            int index = i + 1;
            Note note = notesList.get(i);
            response += index + ". " + note.toString() + "\n";
        }
        return response;
    }
}
