public class Task {
    private boolean completed;
    private String task_name;

    //Constructor
    public Task(String task_name) {
        task_name = task_name;
        completed = false;
    }

    public void mark_as_completed() {
        completed = true;
    }

    @Override
    public String toString() {
        return task_name;
    }
}
