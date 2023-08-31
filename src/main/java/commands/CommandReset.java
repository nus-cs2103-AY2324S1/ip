package commands;

import client.Rock;
import io.Parser;
/**
 * Representation of command
 * used to reset task list.
 * 
 * @author Alvis Ng (supermii2)
 */
public class CommandReset extends Command{
    /**
     * Constructor to create the
     * reset command 
     * @param client Chatbot object
     */
    public CommandReset(Rock client) {
        super(client);
    }
    @Override
    /**
     * Terminates chatbot.
     * @param input Unused.
     */
    public void accept(Parser input) {
        this.client.taskList.reset();
        this.client.storage.saveSaveFile();
        this.client.ui.respond("Task List reset.");
    }
}
