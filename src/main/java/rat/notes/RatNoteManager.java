package rat.notes;

import static rat.io.RatPrinter.printWithLines;

import java.util.ArrayList;
import java.util.Objects;

import rat.storage.RatStorage;

/**
 * This class encapsulates the logic of handling the user's notes.
 * Includes methods to add and delete notes.
 * Handles writing to storage.
 *
 * @author Keagan
 */
public class RatNoteManager {

    /**
     * The list of notes managed by RatNoteManager, represented as an ArrayList.
     */
    private ArrayList<Note> noteList;

    /**
     * The RatStorage object used to store and process the user's notes.
     */
    private RatStorage storage;

    /**
     * Constructor for RatNoteManager.
     * Checks if the file is empty, and if not, reads the data from the file.
     * Updates Note List with data from file.
     * @param storage The RatStorage object used to store and process the user's notes.
     */
    public RatNoteManager(RatStorage storage) {
        this.noteList = new ArrayList<>();
        this.storage = storage;
        if (!storage.isFileEmpty()) {
            getDataFromFile();
        }
    }

    /**
     * Gets the data from the file and updates the note list.
     */
    protected void getDataFromFile() {
        int noteSymbolIndex = 0;
        int noteBodyIndex = 1;
        String[] data = storage.readFile().split("\n");
        for (String s : data) {
            String[] noteData = s.split("/ ");
            if (Objects.equals(noteData[noteSymbolIndex], "N")) {
                Note note = new Note(noteData[noteBodyIndex]);
                this.noteList.add(note);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < noteList.size(); i++) {
            str.append(i + 1).append(". ").append(noteList.get(i)).append("\n");
        }
        return str.toString();
    }

    /**
     * Adds a note to the note list.
     * @param body The body of the note to be added.
     * @return The response to the user echoing a successful note creation.
     */
    public String addNote(String body) {
        Note note = new Note(body);
        this.noteList.add(note);
        String response = "Got it. I've added this note:\n" + note + "\nNow you have " + noteList.size()
                + " notes in the list.";
        printWithLines(response);
        return response;
    }

    /**
     * Deletes a note from the note list.
     * @param index The index of the note to be deleted.
     * @return The response to the user echoing a successful note deletion.
     */
    public String deleteNote(int index) {
        if (index > this.noteList.size() || index < 1) {
            throw new IndexOutOfBoundsException("Note not found");
        } else if (this.noteList.get(index - 1) == null) {
            throw new IndexOutOfBoundsException("Note not found");
        }
        Note item = this.noteList.get(index - 1);
        assert(item != null);
        this.noteList.remove(index - 1);
        String response = "Noted. I've removed this note:\n"
                + item
                + "\nNow you have " + this.noteList.size() + " notes in the list.";
        printWithLines(response);
        return response;
    }

    /**
     * Deletes all notes from the note list.
     * @return The response to the user saying that all notes have been deleted.
     */
    public String deleteAll() {
        this.noteList.clear();
        String response = "Ok. I've removed all notes.";
        printWithLines(response);
        return response;
    }

    /**
     * Lists all notes in the note list.
     * @return The response to the user containing the note list.
     */
    public String listItems() {
        if (this.noteList.isEmpty()) {
            printWithLines("You have no notes in the list.");
            return "You have no notes in the list.";
        } else {
            String list = "Here are the notes in your list:\n"
                    + this + "\n"
                    + "You have " + this.noteList.size() + " notes in the list.";
            printWithLines(list);
            return list;
        }
    }

    /**
     * Saves the note list to the file.
     * Checks if the note list is already in the file, and if not, adds it to the file.
     * @return The response to the user saying that the notes are saved.
     */
    public String save() {
        StringBuilder data = new StringBuilder();
        for (Note note : this.noteList) {
            data.append(note.formatForFile()).append("\n");
        }
        String existingItems = this.storage.readFile();
        if (!existingItems.contains(data.toString())) {
            this.storage.addToFile(data.toString());
        }
        return "Notes saved to file.";
    }
}
