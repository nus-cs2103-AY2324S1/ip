package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.tasks.Task;

import java.time.LocalDate;
import java.util.List;

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
            ui.printToScreen(String.format("%d.%s", ++idx, t.toString()));
        }
    }
}
