package dude.note;

import java.util.ArrayList;

/**
 * Represents a list of notes.
 */
public class NoteList {

    private ArrayList<Note> noteList;

    public NoteList() {
        this.noteList = new ArrayList<Note>();
    }

    public NoteList(ArrayList<Note> noteList) {
        this.noteList = noteList;
    }

    // getters
    public int getSize() {
        return this.noteList.size();
    }

    public Note getNote(int index) {
        return this.noteList.get(index);
    }

    /**
     * Adds a note to the list.
     *
     * @param note Note to be added to the list.
     */
    public void addNote(Note note) {
        int oldNoteListLength = this.getSize();
        noteList.add(note);
        int newNoteListLength = this.getSize();
        assert newNoteListLength == oldNoteListLength + 1
                : "Length of note list after adding note should increase by length 1.";
    }

    /**
     * Removes a note from the list.
     *
     * @param index Index of the note to be removed from the list.
     */
    public Note deleteNote(int index) {
        int oldNoteListLength = this.getSize();
        Note removedNote = noteList.get(index);
        noteList.remove(index);
        int newNoteListLength = this.getSize();
        assert newNoteListLength == oldNoteListLength - 1
                : "Length of note list after deleting note should decrease by length 1.";
        return removedNote;
    }

    /**
     * Finds notes in list that contain specified keyword(s).
     *
     * @param noteKeywords Keywords used to search for notes.
     */
    public NoteList findNotes(String noteKeywords) {
        NoteList searchResults = new NoteList();
        for (int i = 0; i < this.getSize(); i++) {
            Note note = this.getNote(i);
            if (note.containKeywords(noteKeywords)) {
                searchResults.addNote(note);
            }
        }
        return searchResults;
    }
}
