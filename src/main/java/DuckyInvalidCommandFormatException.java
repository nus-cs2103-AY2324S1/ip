public class DuckyInvalidCommandFormatException extends DuckyException {

    public DuckyInvalidCommandFormatException(String message) {
        super(String.format("Um... there's something wrong with your command :(\n%s", message));
    }
}
