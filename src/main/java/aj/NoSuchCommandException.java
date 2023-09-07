package aj;


/**
 * NoSuchCommandException class for exceptions related to unknown commands.
 */
public class NoSuchCommandException extends AjException {

    NoSuchCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(\n Type 'help' to find out more!");
    }
}
