package dude.command;

import dude.Storage;
import dude.note.Note;
import dude.note.NoteList;
import dude.task.TaskList;
import dude.ui.Ui;

import java.io.IOException;

/**
 * Represents a command that deletes note.
 */
public class DeleteNoteCommand extends Command {
    private int noteIndex;

    public DeleteNoteCommand(int noteIndex) {
        this.noteIndex = noteIndex;
    }

    /**
     * Executes the command to delete a note.
     *
     * @param taskList List of tasks.
     * @param noteList List of notes.
     * @param storage Storage containing saved tasks and notes, and handles saving and loading.
     * @param ui User interface of Dude.
     */
    @Override
    public String execute(TaskList taskList, NoteList noteList, Ui ui, Storage storage) {
        String output = "";
        try {
            output = "Executing Delete Note Command\n";
            Note deletedNote = noteList.deleteNote(noteIndex);
            int nNotes = noteList.getSize();
            output = output + ui.showDeletedNote(deletedNote, nNotes) + "\n";
            storage.saveToDisk(taskList, noteList);
        } catch (IOException e) {
            output = "Error in Delete Command";
        }
        return output;
    }
}
