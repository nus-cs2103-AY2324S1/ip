package rock.client.exceptions;
/**
 * Exception used to present
 * possible exceptions thrown during
 * running of the chatbot.
 * @author Alvis Ng (supermii2)
 */
public class RockException extends Exception {
    public RockException(Exception e) {
        super(e);
    }
}
