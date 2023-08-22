package services.bizerrors;

import services.Format;

public class EmptyArgumentException extends JarvisException {
    public EmptyArgumentException(String command) {
        super(Format.format("Sir, I did not catch what you say after the command (%s).\nI beg your pardon.", command));
    }
}
