package alcazar.commands;

import alcazar.Response;
import alcazar.Storage;
import alcazar.TaskList;

/**
 * Encapsulates the command to list the passed tasks
 */
public class ListCommand extends Command {

    /**
     * Executes the command of listing tasks
     * @param tasks the TaskList containing all the tasks upto present
     * @param storage The Storage which stores all the tasks upto now
     * @return A Response listing the added tasks
     */
    @Override
    public Response execute(
            TaskList tasks, Storage storage) {

        String result = "Here are the tasks in your list:\n"
                        + tasks.getTasks();
        return new Response(result, this.isExit());
    }

    /**
     * Checks if this is an exit command
     * @return boolean depending on whether this is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
