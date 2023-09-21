package avalon.command;

import avalon.utility.Storage;
import avalon.task.Task;
import avalon.task.TaskList;
import avalon.utility.Ui;

public class FindCommand extends Command {

    private final String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Searches for tasks containing the specified keyword in their descriptions
     * and displays the matching tasks.
     *
     * @param keywords The keywords to search for in task descriptions.
     */
    private void findTasksByKeyword(Ui ui, TaskList taskList, String... keywords) {
        TaskList matchingTasks = new TaskList();

        for (String keyword : keywords) {
            for (Task task : taskList.tasks()) {
                if (task.getDescription().contains(keyword)) {
                    matchingTasks.addTask(task);
                }
            }
        }

        ui.showFindMessage(matchingTasks);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String[] keywords = userInput.substring(5).trim().split("\\s+");
        findTasksByKeyword(ui, taskList, keywords);
        return ui.getOutput();
    }
}
