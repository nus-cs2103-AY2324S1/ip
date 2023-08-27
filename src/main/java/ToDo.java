/**
 * To Do task.
 */
public class ToDo extends Task{
    public ToDo(String reply) {
        super(reply);
    }

    @Override
    public String toString() {
        String message = super.toString();
        String newMessage = message.substring(0, message.length()).replace("todo", "");
        return String.format("  [T] %s", newMessage);
    }
}
