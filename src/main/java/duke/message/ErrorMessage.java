package duke.message;

public class ErrorMessage extends Message {
    private final String message;
    public ErrorMessage(String message) {
        this.message = message;
    }
    @Override
    public void send() {
        System.out.println(createMessage(message, horizontalLine));
    }
}
