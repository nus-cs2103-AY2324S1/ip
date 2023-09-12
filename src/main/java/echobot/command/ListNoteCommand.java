package echobot.command;

import echobot.note.Note;
import echobot.ui.Ui;
import echobot.storage.Storage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
public class ListNoteCommand extends Command<Note> {
    @Override
    public String doCommand(ArrayList<Note> notes, Ui ui, Storage storage, Scene scene, VBox dialogContainer) {
        if (notes.isEmpty()) {
            return "You don't have any notes yet.";
        }

        StringBuilder response = new StringBuilder("Here are your saved notes:\n");

        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            response.append("    ").append(i + 1).append(". Title: ").append(note.getTitle()).append("\n");
            response.append("        Content: ").append(note.getContent()).append("\n");
        }

        return response.toString();
    }
}

