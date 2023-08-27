/**
 * Deadline task.
 */
public class Deadline extends Task{
    private String due;
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

    public static Deadline create(String status, String description, String due) {
        String newDue = String.format(" /by %s", due);
        Deadline task = new Deadline(description);
//        Deadline task = new Deadline(description + newDue);
        if (status == "1") {
            task.mark();
        }
        return task;
    }

    public String saveToFileLine() {
        return String.format("D | %s", super.saveToFileLine());
    }
}
