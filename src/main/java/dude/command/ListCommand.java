package dude.command;

import dude.NoteList;
import dude.Storage;
import dude.TaskList;
import dude.Ui;

/**
 * Represents a command that lists all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list all tasks.
     *
     * @param taskList List of tasks.
     * @param noteList List of notes.
     * @param storage Storage containing saved tasks, and saves and loads tasks.
     * @param ui User interface of Dude.
     */
    @Override
    public String execute(TaskList taskList, NoteList noteList, Ui ui, Storage storage) {
        String output = "Listing...\n";
        output = output + ui.showTaskList(taskList) + ui.showNoteList(noteList);
        return output;
    }
}
