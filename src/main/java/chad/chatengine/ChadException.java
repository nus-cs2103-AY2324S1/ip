package chad.chatengine;

public class ChadException extends Exception {
    public ChadException(String message) {
        super(message);
    }

    public static class InvalidCommandException extends ChadException {
        public InvalidCommandException(String message) {
            super(message);
        }
    }

    public static class InvalidArgumentException extends ChadException {
        public InvalidArgumentException(String message) {
            super(message);
        }
    }

    public static class InvalidFormatException extends ChadException {
        public InvalidFormatException(String message) {
            super(message);
        }
    }
}
