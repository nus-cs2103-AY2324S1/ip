package services.bizerrors;

/**
 * Represents an exception when Jarvis is unable to read from file.
 */
public class ReadFromFileException extends JarvisException {
    public ReadFromFileException() {
        super("Sorry, sir. I am unable to read your tasks from file.");
    }
}
