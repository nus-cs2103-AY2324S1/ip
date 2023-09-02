package services.bizerrors;

public class InvalidArgumentException extends JarvisException {
    public InvalidArgumentException(String command) {
        super(String.format("Sir, please check again to ensure "
                + "you provide the correct arguments for command (%s).", command));
    }
}
