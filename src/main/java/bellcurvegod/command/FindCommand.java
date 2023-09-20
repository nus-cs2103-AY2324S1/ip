package bellcurvegod.command;

import java.util.ArrayList;

import bellcurvegod.gui.Gui;
import bellcurvegod.task.Task;
import bellcurvegod.tasklist.TaskList;

/**
 * Encapsulates the FindCommand.
 */
public class FindCommand {
    /**
     * Lists all tasks in the taskList that
     * contains the keyword that the user is searching for.
     *
     * @param keyword keyword to search for.
     * @return FindMessage.
     */
    public static String run(String keyword) {
        ArrayList<Task> tasks = TaskList.getTaskList();
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getDescription().contains(keyword)) {
                matchingTasks.add(t);
            }
        }

        return Gui.getFindMessage(matchingTasks);
    }

    public static String getHelpMessage() {
        return "Type 'find <keyword>' and enter, "
            + "the app will list all tasks containing the keyword in the descriptions";
    }
}
