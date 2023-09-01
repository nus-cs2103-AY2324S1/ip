package blip.commands;

import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;

/**
 * Represents find command to find tasks that match description.
 */
public class FindCommand extends Command {
    /**
     * Description of task to find.
     */
    String description;

    /**
     * Constructor of FindCommand.
     * @param description The description of task to find.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the finding of matching tasks in task list.
     * @param tasks The Array List of tasks to find matching description
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     */
    @Override
    public void execute(TaskList tasks, BlipUI ui, BlipStorage storage) {
        if (tasks.size() == 0) {
            ui.showNoMatchingTasksMsg();
        }
        int numOfTasksMatched = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).toString().contains(description)) {
                if (numOfTasksMatched == 0) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.println((i + 1) + "." + tasks.getTask(i).toString());
                numOfTasksMatched++;
            }
        }
        if (numOfTasksMatched == 0) {
            ui.showNoMatchingTasksMsg();
        }
    }
}
