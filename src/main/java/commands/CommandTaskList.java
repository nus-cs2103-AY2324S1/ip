package commands;

import client.Rock;
import io.Parser;

/**
 * Representation of a command
 * to list all tasks in list.
 * @author Alvis Ng (supermii2)
 */
public class CommandTaskList extends Command {
    /**
     * Constructor to create the
     * List command
     * @param client Chatbot object
     */
    public CommandTaskList(Rock client) {
        super(client);
    }
    /**
     * Removes task from task list.
     * @param input Unused
     * @throws IllegalArgumentException Thrown when invalid index is given.
     */
    @Override
    public String apply(Parser input) {
        return(this.client.taskListToString());
    }
}
