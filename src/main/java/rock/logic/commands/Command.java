package rock.logic.commands;
import java.util.function.Function;

import rock.client.Rock;
import rock.logic.io.Parser;
/**
 * Representation of a command
 * that can be issued to the Bot.
 * @author Alvis Ng (supermii2)
 */
public abstract class Command implements Function<Parser, String> {
    protected Rock client;
    /**
     * Constructor method for a command
     * @param client Bot object.
     */
    public Command(Rock client) {
        this.client = client;
    }
    /** Code to be run when command is called. */
    public abstract String apply(Parser input);
}
