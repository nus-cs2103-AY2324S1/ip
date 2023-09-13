package dude.command;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Executing Find Command");
        TaskList searchResults = taskList.findTasks(searchKeywords);
        ui.showTaskList(searchResults);
    }

}
