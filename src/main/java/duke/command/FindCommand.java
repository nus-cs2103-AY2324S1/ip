package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the command to find keywords in tasks.
 */
public class FindCommand extends Command {
    /** Keyword to be searched through task list. */
    private String keyword;

    /**
     * Constructor for the command to find.
     * 
     * @param keyword Keyword to be searched.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> filteredTaskList = taskList.find(keyword);
        ui.printList(filteredTaskList);
    }
}
