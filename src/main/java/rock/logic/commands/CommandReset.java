package rock.logic.commands;

import rock.client.Rock;
import rock.logic.io.Parser;

/**
 * Representation of command
 * used to reset task list.
 * @author Alvis Ng (supermii2)
 */
public class CommandReset extends Command {
    /**
     * Constructor to create the
     * reset command
     * @param client Chatbot object
     */
    public CommandReset(Rock client) {
        super(client);
    }
    /**
     * Terminates chatbot.
     * @param input Unused.
     */
    @Override
     public String apply(Parser input) {
        this.client.getTaskList().reset();
        this.client.saveFile();
        return ("Copy that! Task list has been cleared!");
    }
}
