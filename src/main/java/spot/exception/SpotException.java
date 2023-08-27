package spot.exception;

public class SpotException extends Exception{

    /**
     * Constructs a new SpotException object.
     *
     * @param message Message to be displayed when the SpotException is thrown.
     */
    public SpotException(String message) {
        super(message);
    }

}
