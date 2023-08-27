package duke.message;

public abstract class Message {
    protected String createMessage(String... messages) {
        StringBuilder s = new StringBuilder();
        for (String message: messages) {
            s.append(message);
            s.append("\n");
        }
        return s.toString();
    }

    public abstract void send();
}
