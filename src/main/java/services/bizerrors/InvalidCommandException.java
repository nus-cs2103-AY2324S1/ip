package services.bizerrors;

import services.Ui;

public class InvalidCommandException extends JarvisException {
    public InvalidCommandException(String command) {
        super(Ui.format("Sorry, sir. Executing this command (%s) is beyond my capabilities.", command));
    }
}
