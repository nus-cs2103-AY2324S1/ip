package chatbot.tasks;

public class ToDoTask extends Task {
    private static String type = "T";
    public ToDoTask(String name) {
        super(name);
    }

    public ToDoTask(String name, boolean isDone) {
        super(name, isDone);
    }
    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                this.type,
                this.checkIsDone() ? "X" : " ",
                this.getName());
    }
}
