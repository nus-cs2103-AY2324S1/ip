package aichan.command;

import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;
import aichan.task.Task;

/**
 * Represents a command to find task with keyword.
 * Inherits from the Command class.
 */
public class FindCommand extends Command {
    private String[] keywords;
    private int numberOfKeywords;

    /**
     * Constructs a FindCommand object.
     * Initializes the keyword.
     *
     * @param keywords Words to find in task description.
     */
    public FindCommand(String... keywords) {
        this.keywords = keywords;
        this.numberOfKeywords = keywords.length;
    }

    /**
     * Displays the tasks, if they have any of the keywords.
     *
     * @param tasks A list of tasks.
     * @param ui User interface to display message.
     * @param storage Storage for storing and loading tasks.
     * @return Response as String.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "Here are the matching tasks in your list:\n";
        int size = tasks.getSize();
        int matchingTaskNumber = 0;

        for (int i = 1; i <= size; i++) {
            Task task = tasks.getTask(i);

            for (int keywordIndex = 0; keywordIndex < numberOfKeywords; keywordIndex++) {
                String keyword = keywords[keywordIndex];
                if (keyword.equals("")) {
                    break;
                }

                if (task.hasKeyword(keyword)) {
                    response = response + i + "." + task.toString() + "\n";
                    matchingTaskNumber++;
                    break;
                }
            }
        }
        if (matchingTaskNumber == 0) {
            response = response + "------None task matches the keyword------";
        }
        return response;
    }
}
