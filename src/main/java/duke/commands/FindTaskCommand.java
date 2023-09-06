package duke.commands;

import java.util.ArrayList;

import duke.tasks.TaskList;
import duke.ui.CliUi;

/**
 * Represents a command to find tasks containing a user query in their
 * description.
 */
public class FindTaskCommand extends Command {

    /**
     * Constructs a new FindTaskCommand object.
     *
     * @param taskList The current list of tasks.
     * @param args     The arguments supplied by the user.
     */
    public FindTaskCommand(TaskList taskList, String args) {
        super(CommandType.FIND_TASK, taskList, args);
    }

    /**
     * Finds tasks containing a user query in their description.
     *
     * @return The response to the user.
     */
    @Override
    public String execute() {
        ArrayList<Integer> found = this.taskList.findTasks(this.args);
        if (found.size() == 0) {
            CliUi.println("No tasks found containing your query.");
            return "No tasks found containing your query.";
        } else {
            int[] indexes = found.stream().mapToInt(i -> i).toArray();
            ArrayList<String> lines = new ArrayList<>();
            lines.add("Nice! I've found some related tasks:");
            for (int i : indexes) {
                lines.add((i + 1) + ". " + this.taskList.getTask(i).toString());
            }
            CliUi.printlns(lines.toArray(new String[lines.size()]));
            return String.join("\n", lines);
        }
    }
}
