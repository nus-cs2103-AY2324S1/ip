package duke.command;

import duke.task.TaskStorage;

/**
 * Find tasks that contain a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskStorage taskStorage) {
        return taskStorage.find(keyword);
    }
}
