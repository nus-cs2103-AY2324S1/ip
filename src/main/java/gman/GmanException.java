package gman;

/**
 * Exception class used by Gman.
 */
public class GmanException extends Exception {

    /**
     * Simple exception for Gman.
     * @param message the message to be printed to the user in the GUI.
     */
    public GmanException(String message) {
        super(message);
    }

    /**
     * toString method for exceptions.
     * @return the message in a String for the exception.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
