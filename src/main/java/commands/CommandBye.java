package commands;

import client.Rock;
import io.Parser;

/**
 * Representation of command
 * used to terminate program.
 * @author Alvis Ng (supermii2)
 */
public class CommandBye extends Command {
    /**
     * Constructor to create the
     * Terminate command 
     * @param client Chatbot object
     */
    public CommandBye(Rock client) {
        super(client);
    }
    /**
     * Terminates chatbot.
     * @param input Unused.
     */
    @Override
    public void accept(Parser input) {
        this.client.terminate();
    }
}
