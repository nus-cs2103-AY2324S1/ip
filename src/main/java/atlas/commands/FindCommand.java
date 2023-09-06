package atlas.commands;

import java.util.List;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.components.Ui;
import atlas.tasks.Task;


/**
 * Command to find all events that contain the keyword
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand object
     * @param keyword Keyword to query for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasksWithKeyword = taskList.getTasksWithKeyword(keyword);
        ui.printToScreen("Here are the matching tasks in your list:");
        int idx = 0;
        for (Task t : tasksWithKeyword) {
            ui.printToScreen(String.format("%d. %s", ++idx, t.toString()));
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        List<Task> tasksWithKeyword = taskList.getTasksWithKeyword(keyword);
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        int idx = 0;
        for (Task t : tasksWithKeyword) {
            output.append(String.format("%d. %s\n", ++idx, t.toString()));
        }
        return output.toString();
    }
}
