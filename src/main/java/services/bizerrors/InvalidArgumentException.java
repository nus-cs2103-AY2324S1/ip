package services.bizerrors;

import services.Format;

public class InvalidArgumentException extends JarvisException {
    public InvalidArgumentException(String command) {
        super(Format.format("Sir, please check again to ensure you provide the correct arguments for command (%s).", command));
    }
}
