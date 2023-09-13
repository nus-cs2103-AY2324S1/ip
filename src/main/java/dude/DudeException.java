package dude;

/**
 * Class of Exceptions that might be found in the Dude programme.
 */
public class DudeException extends Exception {
    DudeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
