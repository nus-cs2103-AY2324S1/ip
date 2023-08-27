/**
 * Event task.
 */
public class Event extends Task{
    public Event(String reply) {
        super(reply);
    }

    @Override
    public String toString() {
        String message = super.toString();
        String from = message.substring(message.indexOf("/from") + 6, message.indexOf("/to"));
        String to = message.substring(message.indexOf("/to") + 4);
        String newMessage = message.substring(0, message.indexOf("/from")).replace("event", "");
        return String.format("  [E] %s(from: %sto: %s)", newMessage, from, to);
    }

    public static Deadline create(String status, String description, String from, String to) {
        String newFrom = String.format(" /from %s", from);
        String newTo = String.format(" /to %s", to);
        Deadline task = new Deadline(description + newFrom + newTo);
        if (status == "1") {
            task.mark();
        }
        return task;
    }

    public String saveToFileLine() {
        return String.format("E | %s", super.saveToFileLine());
    }
}
