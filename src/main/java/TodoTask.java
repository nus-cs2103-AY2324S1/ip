public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }
    
    String TODO_TASK_TYPE = "[T]";

    public String toString() {
        return TODO_TASK_TYPE + super.toString();
    }
}