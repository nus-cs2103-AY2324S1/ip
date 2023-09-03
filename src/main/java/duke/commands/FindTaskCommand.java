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

    @Override
    public void execute() {
        List<Task> matchedTasks = dukeBot.searchTasks(keyword.trim());
        uiService.printFoundTasks(matchedTasks, keyword);
    }
}
