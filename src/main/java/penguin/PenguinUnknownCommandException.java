package penguin;
/**
 * Any error specific to an unknown command issued to Penguin bot will throw a <tt>PenguinUnknownCommandException</tt>.
 */
public class PenguinUnknownCommandException extends PenguinException{
    public PenguinUnknownCommandException() {
        super("My bird brain can't understand that!");
    }
}
