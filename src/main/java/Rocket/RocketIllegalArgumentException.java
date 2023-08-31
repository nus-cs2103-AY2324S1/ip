package Rocket;

/**
 * Takes the description of the illegal argument and says that it cannot be empty
 */
public class RocketIllegalArgumentException extends RocketException{
    public RocketIllegalArgumentException(String message) {
        super(message + " cannot be empty.");
    }
}
