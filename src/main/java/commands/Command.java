package commands;
import java.util.function.Consumer;

import client.Rock;
import io.Parser;
/**
 * Representation of a command
 * that can be issued to the chatbot.
 * @author Alvis Ng (supermii2)
 */
public abstract class Command implements Consumer<Parser> {
    protected Rock client;
    public Command(Rock client) {
        this.client = client;
    }
    /** Code to be run when command is called. */
    public abstract void accept(Parser input);
}
