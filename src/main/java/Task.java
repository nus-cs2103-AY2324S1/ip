import java.util.*;

public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void SetDoneOrNot(boolean doneOrNot) {
        this.done = doneOrNot;
    }

    @Override
    public String toString() {
        String done = this.done ? "[X]" : "[ ]";
        return done + " " + this.name;
    }
}
