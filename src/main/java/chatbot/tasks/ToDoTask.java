package chatbot.tasks;

/**
 * Class that represents a to-do task scheduled by user.
 */
public class ToDoTask extends Task {
    private static String type = "T";
    public ToDoTask(String name) {
        super(name);
    }

    public ToDoTask(String name, boolean isDone, Priority priority) {
        super(name, isDone, priority);
    }
    @Override
    public String toString() {
        return String.format("[%s][%s] (%s) %s",
                this.type,
                this.checkIsDone() ? "X" : " ",
                this.getPriority(),
                this.getName());
    }
}
