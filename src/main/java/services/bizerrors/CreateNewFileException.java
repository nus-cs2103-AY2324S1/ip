package services.bizerrors;

public class CreateNewFileException extends JarvisException {
    public CreateNewFileException() {
        super("Sorry, sir. I am unable to create a new file.");
    }
}
