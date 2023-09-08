package sisyphus;

/**
 * Class for exceptions related to Sisyphus chatbot.
 */
public class SisyphusException extends Exception {
    public SisyphusException(String message) {
        super(message + "\nOtherwise, I can't be bothered with it.");
    }

}
