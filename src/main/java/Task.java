public class Task {
    private boolean completed;
    private String task_name;

    //Constructor
    public Task(String name) {
        task_name = name;
        completed = false;
    }

    public void mark_as_completed() {
        completed = true;
    }

    public void mark_as_incompleted() {
        completed = false;
    }
    @Override
    public String toString() {
        String indicator = " ";
        if(completed) {
            indicator = "X";
        }
        return "[" + indicator + "] " + task_name;
    }
}
