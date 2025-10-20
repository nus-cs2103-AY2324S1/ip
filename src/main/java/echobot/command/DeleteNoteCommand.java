package echobot.command;

import java.util.ArrayList;

import echobot.note.Note;
import echobot.storage.Storage;
import javafx.scene.layout.VBox;

/**
 * Represents a command to delete a task from the list.
 */
public class DeleteNoteCommand extends Command<Note> {
    private int taskNum;
    private String responseText;
    private String indent2Spaces = "  ";

    /**
     * Constructs a DeleteCommand instance.
     *
     * @param taskNum The task number to be deleted.
     */
    public DeleteNoteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String doCommand(ArrayList<Note> notes, Storage storage, VBox dialogContainer) {
        if (taskNum <= 0) {
            responseText = "Sorry, the note doesn't exist.";
        } else if (taskNum >= 1 && taskNum <= notes.size()) {
            Note deletedNote = notes.remove(taskNum - 1);

            responseText = "Noted. I've removed this note:\n";
            responseText += indent2Spaces + deletedNote.display() + "\n";
            responseText += "Now you have " + notes.size() + " notes in the list.\n";

            storage.saveNotes(notes, dialogContainer); // Save after deleting
        } else {
            responseText = "Sorry, you only have " + notes.size() + " notes in your list.\n";
        }

        return responseText;
    }

    public String getResponse() {
        return responseText;
    }
}

