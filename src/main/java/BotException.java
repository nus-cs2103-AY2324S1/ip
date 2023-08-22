public class BotException extends Exception {
    public BotException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "____________________________________________________________\n" +
                super.getMessage() +
                "\n____________________________________________________________\n";
    }
}
