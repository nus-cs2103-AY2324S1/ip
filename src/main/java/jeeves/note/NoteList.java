package jeeves.note;

import java.util.ArrayList;

/**
 * Contains logic related to the Note list.
 */
public class NoteList {

    /**
     * The arraylist used to track tasks.
     * Due to how the taskCount variable is used as the id and
     * array access position, index 0 will always be unused.
     * taskList is effectively 1-indexed
     */
    private final ArrayList<Note> noteList;

    public NoteList(ArrayList<Note> notes) {
        noteList = notes;
    }

    public void add(Note newNote) {
        noteList.add(newNote);
    }

    public Note getNote(int index) {
        return noteList.get(index);
    }

    public String getNotelistDataAsString() {
        StringBuilder sb = new StringBuilder();
        for (Note currNote : noteList) {
            sb.append(currNote).append("\n");
        }
        return sb.toString();
    }

}
