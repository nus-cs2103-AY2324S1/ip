package dude.command;

import dude.Note;
import dude.NoteList;
import dude.Storage;
import dude.TaskList;
import dude.Ui;
import dude.task.ToDo;

import java.io.IOException;

public class AddNoteCommand extends Command {

    private String noteDescription;

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
        String output = "";
        try {
            output = "Executing Add Note Command\n";
            Note newNote = new Note(noteDescription);
            noteList.addNote(newNote);
            int nTasks = noteList.getSize();
            output = output + ui.showAddedNote(newNote, nTasks) + "\n";
            storage.saveTasksToDisk(taskList, noteList);
            // storage.saveNotesToDisk(noteList);
        } catch (IOException e) {
            System.out.println("Error in Add ToDo Command");
        }
        return output;
    }
}
