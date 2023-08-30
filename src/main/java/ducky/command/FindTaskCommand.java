package ducky.command;

import ducky.Storage;
import ducky.TaskList;
import ducky.UserInterface;
import ducky.task.Task;

import java.util.ArrayList;

/**
 * Represents a command that finds tasks containing a given query.
 */
public class FindTaskCommand extends Command {

    private final String queryString;

    /**
     * Constructs a command to find tasks containing the specified query.
     * @param queryString Query to find tasks in task list.
     */
    public FindTaskCommand(String queryString) {
        this.queryString = queryString;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) {
        ArrayList<Task> matches = taskList.findTasks(this.queryString);
        if (matches.isEmpty()) {
            ui.showMessagePerLine(
                    String.format(
                            "Sorry, I couldn't find any tasks that contain \"%s\"",
                            queryString
                    )
            );
        } else {
            StringBuilder result = new StringBuilder(
                    "Here are the task(s) that contain \"%s\":\n"
            );
            for (Task t : matches) {
                result.append(String.format("%s\n", t.toString()));
            }

            ui.showMessagePerLine(result.toString());
        }
    }
}
