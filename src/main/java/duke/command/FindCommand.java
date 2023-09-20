package duke.command;

import duke.main.Storage;
import duke.task.TaskList;

/**
 * A class for the command for finding tasks in list using keywords
 */
public class FindCommand extends Command {

    /** Keywords to search for in the task description*/
    private final String keywords;

    /**
     * The constructor for FindCommand
     * @param keywords Keywords to search for in the task description
     */
    public FindCommand(String keywords) {
        this.keywords = keywords.toLowerCase().trim();
    }

    @Override
    public String execute(TaskList taskList, Storage storage, CommandList commandList, boolean write) {
        return this.printCommand(taskList);
    }

    @Override
    public String printCommand(TaskList taskList) {
        String result = "JonBird:\n\tHere are the matched tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            String description = taskList.getTask(i).getDescription().toLowerCase();
            if (description.contains(this.keywords)) {
                result += "\n\t\t" + (i + 1) + ". " + taskList.getTask(i).printTask();
            }
        }
        return result;
    }

    @Override
    public boolean isContinue() {
        return true;
    }
}
