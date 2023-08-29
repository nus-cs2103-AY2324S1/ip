package services.bizerrors;

public class ReadFromFileException extends JarvisException {
    public ReadFromFileException() {
        super("Sorry, sir. I am unable to read your tasks from file.");
    }
}
