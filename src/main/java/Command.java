import java.util.function.Consumer;
/**
 * Representation of a command
 * that can be issued to the chatbot.
 * 
 * @author Alvis Ng (supermii2)
 */
public abstract class Command implements Consumer<Parser> {
    Rock client;
    Command(Rock client) {
        this.client = client;
    }
    /** Code to be run when command is called. */
    public abstract void accept(Parser input);
}