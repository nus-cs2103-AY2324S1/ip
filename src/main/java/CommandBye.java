/**
 * Representation of command
 * used to terminate program.
 * 
 * @author Alvis Ng (supermii2)
 */
public class CommandBye extends Command{
    CommandBye(Rock client) {
        super(client);
    }
    @Override
    /**
     * Terminates chatbot.
     * @param input Unused.
     */
    public void accept(Parser input) {
        this.client.terminate();
    }
}
