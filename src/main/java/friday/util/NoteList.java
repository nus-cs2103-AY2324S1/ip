package friday.util;

import java.util.ArrayList;

import friday.item.Note;


/**
 * Represents a list of notes in the Friday application.
 */
public class NoteList {
    private ArrayList<Note> noteList;

    public NoteList(Storage noteStorage) {
        noteList = noteStorage.readNotesItems();
    }

    /**
     * Adds a note to the note list.
     *
     * @param note The note to be added.
     */
    public void add(Note note) {
        noteList.add(note);
    }

    /**
     * Deletes a specific note from the list.
     *
     * @param noteNumber The index of the note to be deleted.
     * @return A string message indicating the note has been removed.
     */
    public String delete(int noteNumber) {
        if (noteList.isEmpty()) {
            return "The note list is empty. Nothing to delete!";
        }

        if (noteNumber < 0 || noteNumber >= noteList.size()) {
            return "Invalid note number. Please provide a valid note index number.";
        }

        Note note = noteList.get(noteNumber);
        noteList.remove(noteNumber);
        return "I've removed this note:\n" + note + "\n" + message();
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < noteList.size(); i++) {
            result.append((i + 1)).append(". ").append(noteList.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Returns a message indicating the number of notes in the list.
     *
     * @return A string message showing the count of notes.
     */
    public String message() {
        return "Now you have " + noteList.size() + " notes in the list.";
    }
}
