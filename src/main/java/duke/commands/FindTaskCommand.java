package duke.commands;

import java.util.List;

import duke.Duke;
import duke.service.UiService;
import duke.tasks.Task;

/**
 * Represents a Command to find tasks based on the provided keyword.
 */
public class FindTaskCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindTaskCommand
     *
     * @param dukeBot The Duke instance.
     * @param uiService The UI service for interactions.
     * @param keyword The keyword to look for in the taskList.
     */
    public FindTaskCommand(Duke dukeBot, UiService uiService, String keyword) {
        super(dukeBot, uiService);
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks based on the provided keyword.
     *
     * @return A string representing the tasks that matched the keyword. If no tasks matched,
     *         it returns a string indicating that no tasks were found.
     */
    @Override
    public String execute() {
        List<Task> matchedTasks = dukeBot.searchTasks(keyword.trim());
        return uiService.formatFoundTasks(matchedTasks, keyword);
    }
}
