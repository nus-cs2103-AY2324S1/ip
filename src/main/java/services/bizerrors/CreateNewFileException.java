package services.bizerrors;

/**
 * Represents an exception when Jarvis is unable to create a new file.
 */
public class CreateNewFileException extends JarvisException {
    public CreateNewFileException() {
        super("Sorry, sir. I am unable to create a new file.");
    }
}
