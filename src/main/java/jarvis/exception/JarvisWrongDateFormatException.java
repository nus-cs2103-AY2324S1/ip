package jarvis.exception;

public class JarvisWrongDateFormatException extends JarvisException {
    public JarvisWrongDateFormatException() {
        super("Please use the correct date time format: d/M/yyyy HHmm.");
    }
}
