
public class Task {
    public static final String EXIT_COMMAND = "bye";

    public static final String LIST_COMMAND = "list";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";

    private String task;
    private boolean isDone = false;


    Task(String task) {
        this.task = task;
    }

    public boolean isExit() {
        return this.task.equals(Task.EXIT_COMMAND);
    }

    public String get() {
        return this.task;
    }

    public boolean isList() {
        return this.task.equals(Task.LIST_COMMAND);
    }

    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : "";
    }

    public boolean isMark() {
        return this.task.contains("mark");
    }

    public boolean isUnmark() {
        return this.task.contains("unmark");
    }

    public boolean isDone() {
        return this.isDone;
    }

}
