/**
 * Deadline task.
 */
public class Deadline extends Task{
    public Deadline(String reply) {
        super(reply);
    }

    @Override
    public String toString() {
        String message = super.toString();
        String due = message.substring(message.indexOf("/by") + 4);
        String newMessage = message.substring(0, message.indexOf("/by")).replace("deadline", "");
        return String.format("  [D] %s(by: %s)", newMessage, due);
    }
}
