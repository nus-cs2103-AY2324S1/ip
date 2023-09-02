package aj;


/**
 * NoSuchCommandException class for exceptions related to unknown commands.
 */
public class NoSuchCommandException extends AjException {

    NoSuchCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(\n" + "Heres some commands for you:\n" + "type "
                + "\"list\" to list tasks\n" + "type \"todo\" to view available todo options\n" + "type \"deadline\" "
                + "to" + " view available deadline options\n" + "type \"event\" to view available event options\n");
    }
}
