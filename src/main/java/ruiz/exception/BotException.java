package ruiz.exception;
public class BotException extends Exception {
    public BotException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * This method coverts the value of the BotException into a String type.
     * @return a String representation of the Exception with its error message.
     */
    @Override
    public String toString() {
        return "____________________________________________________________\n" +
                super.getMessage() +
                "\n____________________________________________________________\n";
    }
}
