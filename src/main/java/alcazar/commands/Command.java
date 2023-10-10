package alcazar.commands;

import alcazar.Response;
import alcazar.Storage;
import alcazar.TaskList;
import alcazar.exceptions.AlcazarException;


/**
 * Encapsulates a Command object
 */
public abstract class Command {

    /**
     * Executes the Command
     * @param tasks the TaskList containing all the tasks upto present
     * @param storage The Storage which stores all the tasks upto now
     * @return A Response to showcase that the command has been executed
     * @throws AlcazarException If an empty command or out of bounds serial is passed
     */
    public abstract Response execute(
            TaskList tasks, Storage storage) throws AlcazarException;

    /**
     * Checks if this is an exit command
     * @return boolean depending on whether this is an exit command
     */
    public abstract boolean isExit();
}