package dude.command;

import dude.Storage;
import dude.note.NoteList;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Represents a command that finds tasks by keywords.
 */
public class FindCommand extends Command {

    private String searchKeywords;

    /**
     * Creates a Find Command that finds tasks by specified keywords.
     * @param searchKeywords Keywords used in search for tasks.
     */
    public FindCommand(String searchKeywords) {
        this.searchKeywords = searchKeywords;
    }

    @Override
    public String execute(TaskList taskList, NoteList noteList, Ui ui, Storage storage) {
        TaskList searchResults = taskList.findTasks(searchKeywords);
        String output = ui.showTaskList(searchResults) + "\n";
        return output;
    }
}
