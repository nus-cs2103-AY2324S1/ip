package echobot.command;

import echobot.note.Note;
import echobot.storage.Storage;
import echobot.ui.Ui;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class AddNoteCommand extends Command<Note> {
    private String title;
    private String content;
    private String responseText;

    public AddNoteCommand(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String doCommand(ArrayList<Note> notes, Ui ui, Storage storage, Scene scene, VBox dialogContainer) {
        Note newNote = new Note(title, content);

        notes.add(newNote);

        responseText = "Note added: " + title;

        storage.saveNotes(notes, dialogContainer);

        return responseText;
    }
}
