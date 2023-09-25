package alcazar.commands;

import alcazar.Response;
import alcazar.Storage;
import alcazar.TaskList;

/**
 * Encapsulates the command to exit
 */
public class ExitCommand extends Command {

    /**
     * Executes the command of exiting
     * @param tasks the TaskList containing all the tasks upto present
     * @param storage The Storage which stores all the tasks upto now
     * @return A String response to showcase that the user's exit
     */
    @Override
    public Response execute(
            TaskList tasks, Storage storage) {
        String result = "Goodbye my Master! I hope to be of service soon.";
        return new Response(result, this.isExit());
    }

    /**
     * Checks if this is an exit command
     * @return boolean depending on whether this is an exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
