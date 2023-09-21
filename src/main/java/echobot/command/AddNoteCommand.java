package echobot.command;

import java.util.ArrayList;

import echobot.note.Note;
import echobot.storage.Storage;
import javafx.scene.layout.VBox;

/**
 * The AddNoteCommand class represents a command to add a new note.
 */
public class AddNoteCommand extends Command<Note> {
    private String title;
    private String content;
    private String responseText;

    /**
     * Constructs an AddNoteCommand with the specified title and content for the new note.
     *
     * @param title   The title of the new note.
     * @param content The content of the new note.
     */
    public AddNoteCommand(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * Executes the AddNoteCommand to add a new note.
     *
     * @param notes          The list of notes to which the new note will be added.
     * @param storage        The storage for saving notes.
     * @param dialogContainer The container for displaying dialog messages.
     * @return A response message indicating the result of the command.
     */
    @Override
    public String doCommand(ArrayList<Note> notes, Storage storage, VBox dialogContainer) {
        Note newNote = new Note(title, content);

        notes.add(newNote);

        responseText = "Note added: " + title + "\n";
        responseText += "Now you have " + notes.size() + " notes in the list.\n";

        storage.saveNotes(notes, dialogContainer);

        return responseText;
    }
}
