package services.bizerrors;

public class SaveToFileException extends JarvisException {
    public SaveToFileException() {
        super("Sorry, sir. I am unable to save your tasks to file.");
    }
}
