package services.bizerrors;

/**
 * Represents an exception when Jarvis is unable to save to file.
 */
public class SaveToFileException extends JarvisException {
    public SaveToFileException() {
        super("Sorry, sir. I am unable to save your tasks to file.");
    }
}
