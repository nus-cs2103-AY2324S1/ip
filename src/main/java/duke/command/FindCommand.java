package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command functions to find a task that contains a specified keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        TaskList tasksWithKeyword = new TaskList();
        for (Task task: tasks) {
            if (task.contains(keyword)) {
                tasksWithKeyword.add(task);
            }
        }
        return ui.find(tasksWithKeyword, keyword);
    }
}
