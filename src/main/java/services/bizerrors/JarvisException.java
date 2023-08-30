package services.bizerrors;

public class JarvisException extends Exception {
    public JarvisException(String message) {
        // add the newline for formatting of the error message
        super(message);
    }
}
