enum TaskType {
    todo, event, deadline
}

public class Task {
    protected String name;
    protected boolean done;

    protected Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void SetDoneOrNot(boolean doneOrNot) {
        this.done = doneOrNot;
    }
}

