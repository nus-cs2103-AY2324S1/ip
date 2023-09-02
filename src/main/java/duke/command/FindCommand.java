package duke.command;

import duke.main.Storage;
import duke.task.TaskList;
import duke.task.Task;

/**
 * A class for the command for finding tasks in list using keywords
 */
public class FindCommand extends Command {

    /** Keywords to search for in the task description*/
    private String keywords;

    /**
     * The constructor for FindCommand
     * @param keywords Keywords to search for in the task description
     */
    public FindCommand(String keywords) {
        this.keywords = keywords.toLowerCase().trim();
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        TaskList tempList = new TaskList();
        for (Task task : taskList.getTaskList()) {
            String description = task.getDescription().toLowerCase();
            if (description.contains(this.keywords)) {
                tempList.addTask(task);
            }
        }
        this.printCommand(tempList);
    }

    @Override
    public void printCommand(TaskList filteredTaskList) {
        System.out.println("JonBird:");
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < filteredTaskList.size(); i++) {
            System.out.println("\t\t"+ (i+1) + ". " + filteredTaskList.getTask(i).printTask());
        }
    }
    @Override
    public boolean isContinue() {
        return true;
    }
}
