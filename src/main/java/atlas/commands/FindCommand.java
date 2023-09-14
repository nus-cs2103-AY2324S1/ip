package atlas.commands;

import java.util.List;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.tasks.Task;


/**
 * Command to find all events that contain the keyword
 */
public class FindCommand extends MultiTaskCommand {
    private static final String OUTPUT_HEADER_MESSAGE = "Here are the matching tasks in your list:";
    private final String keyword;

    /**
     * Constructs a FindCommand object
     * @param keyword Keyword to query for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        List<Task> tasksWithKeyword = taskList.getTasksWithKeyword(keyword);
        return generateListOutput(tasksWithKeyword, OUTPUT_HEADER_MESSAGE);
    }
}
