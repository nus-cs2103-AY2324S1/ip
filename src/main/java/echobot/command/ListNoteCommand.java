package echobot.command;

import java.util.ArrayList;

import echobot.note.Note;
import echobot.storage.Storage;
import javafx.scene.layout.VBox;

/**
 * The ListNoteCommand class represents a command to list all saved notes.
 */
public class ListNoteCommand extends Command<Note> {

    /**
     * Executes the ListNoteCommand to list all saved notes.
     *
     * @param notes          The list of notes to be listed.
     * @param storage        The storage for accessing notes.
     * @param dialogContainer The container for displaying dialog messages.
     * @return A message listing all saved notes or an indication that there are no notes.
     */
    @Override
    public String doCommand(ArrayList<Note> notes, Storage storage, VBox dialogContainer) {
        if (notes.isEmpty()) {
            return "You don't have any notes yet.";
        }

        StringBuilder responseText = new StringBuilder("Here are your saved notes:\n");

        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            responseText.append("    ").append(i + 1).append(". Title: ").append(note.getTitle()).append("\n");
            responseText.append("        Content: ").append(note.getContent()).append("\n");
        }

        return responseText.toString();
    }
}

