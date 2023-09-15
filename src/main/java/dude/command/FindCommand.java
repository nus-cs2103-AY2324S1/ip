package dude.command;

import dude.NoteList;
import dude.Storage;
import dude.TaskList;
import dude.Ui;

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
        String output = "Executing Find Command\n";
        TaskList searchResults = taskList.findTasks(searchKeywords);
        output = output + ui.showTaskList(searchResults);
        return output;
    }
}
