package avalon.command;

import avalon.utility.Storage;
import avalon.task.Task;
import avalon.task.TaskList;
import avalon.utility.Ui;

/**
 * Represents a command to find tasks containing specified keywords in their descriptions.
 * This command is triggered by user input "find".
 */
public class FindCommand extends Command {

    private final String userInput;

    /**
     * Constructs a FindCommand with the user input containing keywords to search for.
     *
     * @param userInput The user input containing keywords.
     */
    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Searches for tasks containing the specified keyword in their descriptions
     * and displays the matching tasks.
     *
     * @param ui        The Ui instance for displaying search results.
     * @param taskList  The TaskList to search for tasks in.
     * @param keywords  The keywords to search for in task descriptions.
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

    /**
     * Executes the FindCommand to search for tasks containing specified keywords
     * in their descriptions and displays the matching tasks.
     *
     * @param taskList The TaskList to search for tasks in.
     * @param storage  Not used in this command.
     * @param ui       The Ui instance for displaying search results.
     * @return A string message indicating the result of the command's execution.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String[] keywords = userInput.substring(5).trim().split("\\s+");
        findTasksByKeyword(ui, taskList, keywords);
        return ui.getOutput();
    }
}
