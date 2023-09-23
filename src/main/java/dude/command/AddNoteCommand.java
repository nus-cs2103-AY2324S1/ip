package dude.command;

import dude.note.Note;
import dude.note.NoteList;
import dude.task.TaskList;
import dude.ui.Ui;
import dude.util.Storage;

/**
 * Represents a command that adds an Note.
 */
public class AddNoteCommand extends Command {

    private String noteDescription;

    /**
     * Creates an Add Note Command to add a note to the list.
     * @param noteDescription Short description of note to be added.
     */
    public AddNoteCommand(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    /**
     * Executes the command to add a Note.
     *
     * @param taskList List of tasks.
     * @param storage Storage containing saved notes, and saves and loads notes.
     * @param ui User interface of Dude.
     */
    @Override
    public String execute(TaskList taskList, NoteList noteList, Ui ui, Storage storage) {
        Note newNote = new Note(noteDescription);
        noteList.addNote(newNote);
        int nTasks = noteList.getSize();
        String output = ui.showAddedNote(newNote, nTasks) + "\n";
        storage.saveToDisk(taskList, noteList);
        return output;
    }
}
