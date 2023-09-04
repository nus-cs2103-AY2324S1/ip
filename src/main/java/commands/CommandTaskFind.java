package commands;

import client.Rock;
import io.Parser;
/**
 * Representation of a command
 * to find tasks that contain a word
 * @author Alvis Ng (supermii2)
 */
public class CommandTaskFind extends Command {
    /**
     * Constructor to create the
     * Find commands
     * @param client Chatbot object
     */
    public CommandTaskFind(Rock client) {
        super(client);
    }
    /**
     * Returns all tasks with the given word
     * @param input Contains keyword to check against
     */
    @Override
    public String apply(Parser input) {
        String keyword = input.getDefaultString();
        return client.taskListFilteredSearch(task -> task.getName().contains(keyword));
    }
}
