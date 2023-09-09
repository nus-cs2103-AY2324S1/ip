package duke.command;

import duke.main.Storage;
import duke.task.Task;
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
    public String execute(TaskList taskList, Storage storage) {
        TaskList tempList = new TaskList();
        for (Task task : taskList.getTaskList()) {
            String description = task.getDescription().toLowerCase();
            if (description.contains(this.keywords)) {
                tempList.addTask(task);
            }
        }
        return this.printCommand(tempList);
    }

    @Override
    public String printCommand(TaskList filteredTaskList) {
        String result = "JonBird:\n\tHere are the tasks in your list:";
        for (int i = 0; i < filteredTaskList.size(); i++) {
            result += "\n\t\t" + (i + 1) + ". " + filteredTaskList.getTask(i).printTask();
        }
        return result;
    }
    @Override
    public boolean isContinue() {
        return true;
    }
}
