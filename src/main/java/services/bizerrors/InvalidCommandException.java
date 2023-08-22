package services.bizerrors;

import services.Format;

public class InvalidCommandException extends JarvisException {
    public InvalidCommandException(String command) {
        super(Format.format("Sorry, sir. Executing this command (%s) is beyond my capabilities.", command));
    }
}
