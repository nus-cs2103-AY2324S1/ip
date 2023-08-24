package penguin;
/**
 * Any error specific to Penguin bot will throw a <tt>PenguinException</tt>.
 */
public class PenguinException extends Exception {
    public PenguinException(String message) {
        super(message);
    }
}
