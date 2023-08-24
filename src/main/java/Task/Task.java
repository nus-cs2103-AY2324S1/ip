package Task;
public class Task {
    final private String name;
    private boolean completed = false;

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    protected String insertColonInTime(String time) {
        return time.replaceFirst(" ", ": ");
    }
    public Task(String name) {
        this.name = name;
    }

    public static Task Of(String task, TaskType taskType) {
        switch (taskType) {
            case TODO:
                return new Todo(task);
            case DEADLINE:
                return new Deadline(task);
            case EVENT:
                return new Event(task);
            default:
                return null;
        }
    }
    public void SetCompleted(){
        completed = true;
    }
    public void SetUncompleted(){
        completed = false;
    }
    public String toString() {

        return completed ? "[X] " + name : "[ ] " + name;

    }
}
