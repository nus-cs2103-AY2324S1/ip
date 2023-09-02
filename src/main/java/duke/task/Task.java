package duke.task;

public class Task {

    private String name;

    private boolean done;


    public Task(String task) {
        name = task;
        done = false;
    }

    public String type() {
        return "duke/task";
    }

    public String taskName() {
        return name;
    }

    public void mark() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    public boolean isDone() {
        return done;
    }

    public String status() {
        return done ? "[X]" : "[ ]";
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Task) {
            Task temp = (Task)o;
            return this.name.equals(temp.taskName()) && this.done == temp.isDone();
        }
        return false;
    }
}
