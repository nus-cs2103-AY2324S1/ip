package atlas.commands;

import java.util.List;

import atlas.tasks.Task;


/**
 * Abstract MultiTaskCommand that can either operate on multiple tasks in
 * a TaskList at the same time or return a list of tasks
 */
public abstract class MultiTaskCommand extends Command {

    /**
     * Generates message containing list of tasks operated on/returned
     * @param tasks List of tasks operated on/to return
     * @param headerMessage Message to print before the list of tasks
     * @return Message containing the tasks that are operated on/returned
     */
    protected String generateListOutput(List<Task> tasks, String headerMessage) {
        StringBuilder output = new StringBuilder(headerMessage + "\n");
        int taskIdx = 0;

        for (Task t : tasks) {
            output.append(String.format("\n%d. %s\n", ++taskIdx, t));
            assert taskIdx > 0;
        }
        return output.toString();
    }
}
