package services.bizerrors;

public class EmptyArgumentException extends JarvisException {
    public EmptyArgumentException(String command) {
        super(String.format("Sir, I did not catch what you say after the command (%s).\nI beg your pardon.", command));
    }
}
