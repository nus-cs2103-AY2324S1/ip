package services.bizerrors;

/**
 * Represents an exception when the user does not provide any argument after a command.
 */
public class EmptyArgumentException extends JarvisException {
    public EmptyArgumentException(String command) {
        super(String.format("Sir, I did not catch what you say after the command (%s).\nI beg your pardon.",
                command.toLowerCase()));
    }
}
