import java.time.LocalDate;

public class Task {
    private String task;
    private boolean done;
    private LocalDate deadlineDate;

    public Task() {
        task = "default";
        done = false;
    }
    public Task(String newTask){
        task = newTask;
        done = false;
    }

    public boolean isDone() {
        return done;
    }

    public void done() {
        done = true;
    }

    public void undo() {
        done = false;
    }

    public String getStatusIcon() {
        return (done ? "X" : " ");
    }

    public String fileSaveFormat() {
        return getStatusIcon() + "|" + task;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + task;
    }
}